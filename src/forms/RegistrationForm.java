package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.PersonDao;
import dao.GroupDao;

public final class RegistrationForm {

	private static final String ALGO_CHIFFREMENT = "SHA-256";

	private String resultat;
	private Map<String, String> errors ;
	private PersonDao personDao;
	private GroupDao groupDao;

	public RegistrationForm(PersonDao personDao) {
		this.personDao = personDao;
		errors = new HashMap<String, String>();
	}
	
	public RegistrationForm(PersonDao personDao, GroupDao groupDao) {
		this.personDao = personDao;
		this.groupDao = groupDao;
		errors = new HashMap<String, String>();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResultat() {
		return resultat;
	}

	public Person registerUser(HttpServletRequest request, boolean isCreateBySomeoneElse) {
		String login = getValeurChamp(request, "login");
		String email = getValeurChamp(request, "email");
		String pwd = getValeurChamp(request, "pwd");

		Person person = new Person();
		try {

			// traiterPseudo( pseudoAdmin );
			person.setName(login);
			traiterLogin(login);
			person.setLogin(login);
			traiterEmail(email, person);
			person.setEmail(email);
			person.setNew(true);
			if(! isCreateBySomeoneElse){
				// on crypte le pwd et on verifie la confirmation
				String confirmPwd = getValeurChamp(request, "confirmPwd");
				String newPwdAdmin = traiterMotsDePasse(pwd, confirmPwd);
				person.setPwd(newPwdAdmin);
			// si la personne a été cree par quelqu'un d'autre, pas besoin de crypte le pwd
			}else{
				person.setPwd(pwd);
			}
			if (errors.isEmpty()) {

				long idPerson = personDao.create(person);

				person.setId(idPerson);
				System.out.println("Succès de l'inscription.");
			} else {
				System.out.println("Échec de l'inscription.");
			}
		} catch (DAOException e) {
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
			return null;
		}
		return person;
	}
	
	public void registerUserBySomeoneElse(HttpServletRequest request) {
		//creation de la nouvelle personne
		Person personToAdd = registerUser(request, true);
		
		HttpSession session = request.getSession();
		//Person user = (Person) session.getAttribute("person");
		
		Group group = (Group) session.getAttribute("group");
		
		if(errors.isEmpty()){
			try{
				//TODO enregistrement de cette persone ds la liste de contact de l'utilisateur
				//personDao.addContact(contact, user);
				
				// et dans belongTo
				groupDao.registerGroup(personToAdd.getId(), group.getId(), 0);
				group.addPersonIntoListMembers(personToAdd);
			}catch (DAOException e){
				errors.put("registerUser", "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
				e.printStackTrace();
			}
		}
	}

	public Person modifyLogin(HttpServletRequest request) {

		String login = request.getParameter("login");

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");

		try {
			traiterLogin(login);
			if(errors.isEmpty()){
				personDao.modifyLogin(person, login);
				person.setLogin(login);
				System.out.println("changement de login reussi !");
			}else{
				System.out.println("Echec du changement login reussi !");
			}
		} catch (DAOException e) {
			errors.put("modifyLogin",
					"Fail of the modification of login : thank you to try again in few minutes.");
			e.printStackTrace();
			return null;
		}
		return person;
	}

	public Person modifyName(HttpServletRequest request) {

		String name = request.getParameter("name");

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		try {
			personDao.modifyName(person, name);
			person.setName(name);
			System.out.println("changement de name reussi !");
			return person;
		} catch (DAOException e) {
			errors.put("modifyName",
					"Fail of the modification of name : thank you to try egain in few minutes.");
			e.printStackTrace();
			return null;
		}
	}

	public Person modifyEmail(HttpServletRequest request) {

		String email = request.getParameter("email");

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		try {
			traiterEmail(email, person);
			if(errors.isEmpty()){
				personDao.modifyEmail(person, email);
				person.setEmail(email);
			}
		} catch (DAOException e) {
			errors.put("modifyEmail",
					"Fail of the modification of email : thank you to try egain in few minutes.");
			e.printStackTrace();
			return null;
		}
		return person;
	}
	
	public Person modifyPwd(HttpServletRequest request) {

		String oldPwd = request.getParameter("oldPwd");
		String newPwd = getValeurChamp(request, "newPwd");
		String confirmNewPwd = getValeurChamp(request, "confirmNewPwd");

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		try {
			// we check that the old pwd match the user connected
        	boolean oldPwdOk = personDao.checkUser(person,person.getLogin(), oldPwd);
        	String cryptedPwd = ""; 
        	if(! oldPwdOk){
        		errors.put("oldPwd", "wrong password");
        	}else{
        		// if yes we check that the pwd and the confirmPwd are the same
    			cryptedPwd = traiterMotsDePasse(newPwd, confirmNewPwd);
    			if(errors.isEmpty()){
    				// if everything ok we insert in the database
    				personDao.modifyPwd(person, cryptedPwd);
    				person.setPwd(cryptedPwd);
    			}
       	}
		} catch (DAOException e) {
			errors.put("modifyPwd",
					"Fail of the modification of email : thank you to try egain in few minutes.");
			e.printStackTrace();
			return null;
		}
		return person;
	}

	/*
	 * Appel à la validation du nom reçu et initialisation de la propriété nom
	 * du bean
	 */
	private void traiterLogin(String login) {
		try {
			validationLogin(login);
		} catch (FormValidationException e) {
			System.out.println("le login existe déjà!!");
			errors.put("loginPerson", e.getMessage());
		}
	}

	/* Validation du nom */
	private void validationLogin(String login) throws FormValidationException {
		if (login != null && login.length() < 3) {
			System.out
					.println("Le login utilisateur doit contenir au moins 3 caractères.");
			throw new FormValidationException(
					"Le login utilisateur doit contenir au moins 3 caractères.");
		}

		// TODO checker si le login exist pas ds la bdd
		else if (!personDao.checkLogin(login)) {
			throw new FormValidationException(
					"Le login utilisateur existe déjà.");

		}
	}

	/*
	 * Appel à la validation du nom reçu et initialisation de la propriété nom
	 * du bean
	 */
	private void traiterNom(String name, Group group) {
		try {
			validationNom(name);
		} catch (FormValidationException e) {
			System.out.println("mauvais nom.");
			errors.put("nameGroup", e.getMessage());
		}
	}

	/* Validation du nom */
	private void validationNom(String nameGroup) throws FormValidationException {
		if (nameGroup != null && nameGroup.length() < 3) {
			System.out
					.println("Le nom d'utilisateur doit contenir au moins 3 caractères.");
			throw new FormValidationException(
					"Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}

		// TODO checker si le nom exist pas ds la bdd
		// else if (){
		//
		// }
	}

	/*
	 * Appel à la validation de l'adresse email reçue et initialisation de la
	 * propriété email du bean
	 */
	private void traiterEmail(String email, Person person) {
		try {
			System.out.println("Email: " + email);
			validationEmail(email);
		} catch (FormValidationException e) {
			System.out.println("mauvaise adresse mail");
			errors.put("emailPerson", e.getMessage());
		}
	}

	/* Validation de l'adresse email */
	private void validationEmail(String email) throws FormValidationException {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				System.out.println("Merci de saisir une adresse mail valide.");

				throw new FormValidationException(
						"Merci de saisir une adresse mail valide.");
				// } else if ( groupDao.trouver( email ) != null ) {
				// System.out.println("Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
				// throw new FormValidationException(
				// "Cette adresse email est déjà utilisée, merci d'en choisir une autre."
				// );
			}
		}
	}

	/*
	 * Appel à la validation des mots de passe reçus, chiffrement du mot de
	 * passe et initialisation de la propriété motDePasse du bean
	 */
	private String traiterMotsDePasse(String pwd, String confirmation) {
		try {
			validationMotsDePasse(pwd, confirmation);
		} catch (FormValidationException e) {
			System.out.println("mauvais mdp");

			errors.put("pwd", e.getMessage());
		} catch (SecondException e) {

			errors.put("confirmPwd", e.getMessage());
		}

		/*
		 * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
		 * efficacement.
		 * 
		 * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
		 * aléatoire et un grand nombre d'itérations de la fonction de hashage.
		 * 
		 * La String retournée est de longueur 56 et contient le hash en Base64.
		 */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String pwdChiffre = passwordEncryptor.encryptPassword(pwd);

		return pwdChiffre;
	}

	/* Validation des mots de passe */
	private void validationMotsDePasse(String motDePasse, String confirmation)
			throws FormValidationException, SecondException {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				System.out
						.println("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
				throw new SecondException("Password different from the first");
			} else if (motDePasse.length() < 3) {
				System.out
						.println("Les mots de passe doivent contenir au moins 3 caractères.");
				throw new FormValidationException(
						"Les mots de passe doivent contenir au moins 3 caractères.");
			}
		}
		// TODO verifier que le pwd est unique dans le group
		// else if(){
		//
		// }

		else {
			System.out
					.println("Merci de saisir et confirmer votre mot de passe.");
			throw new FormValidationException(
					"Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
