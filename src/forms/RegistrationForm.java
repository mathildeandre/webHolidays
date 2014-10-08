package forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.PersonDao;

public final class RegistrationForm {
//    private static final String CHAMP_EMAIL      = "email";
//    private static final String CHAMP_PASS       = "motdepasse";
//    private static final String CHAMP_CONF       = "confirmation";
//    private static final String CHAMP_NOM        = "nameGroup";

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private PersonDao     personDao;

    public RegistrationForm(PersonDao personDao ) {
        this.personDao = personDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Person inscrireUtilisateur( HttpServletRequest request ) {
        String login = getValeurChamp( request, "login" );
        String email = getValeurChamp( request, "email" );
        String pwd = getValeurChamp( request, "pwd" );
        String confirmPwd = getValeurChamp( request, "confirmPwd" );

        Person person = new Person();
        try {

            //traiterPseudo( pseudoAdmin );
            person.setName(login);
            traiterLogin(login);
            person.setLogin(login);
            traiterEmail( email, person );
            person.setEmail( email );
            person.setNew(true);
            String newPwdAdmin = traiterMotsDePasse( pwd, confirmPwd );
            person.setPwd(newPwdAdmin);

            if ( erreurs.isEmpty() ) {
                
                long idPerson = personDao.create( person );
                
                person.setId(idPerson);
                System.out.println( "Succès de l'inscription.");
            } else {
            	 System.out.println( "Échec de l'inscription.");
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Appel à la validation du nom reçu et initialisation de la propriété nom
     * du bean
     */
    private void traiterLogin( String login ) {
        try {
            validationLogin( login );
        } catch ( FormValidationException e ) {
        	System.out.println("le login existe déjà!!");
            setErreur( "loginPerson", e.getMessage() );
        }
    }
    
    /* Validation du nom */
    private void validationLogin( String login) throws FormValidationException {
        if ( login != null && login.length() < 3 ) {
        	System.out.println( "Le nom d'utilisateur doit contenir au moins 3 caractères.");
            throw new FormValidationException( "Le login utilisateur doit contenir au moins 3 caractères." );
        }
        
//        TODO checker si le login exist pas ds la bdd
        else if (! personDao.checkLogin(login)){ 
            throw new FormValidationException( "Le login utilisateur existe déjà." );
        	
        }
    }
    
    /*
     * Appel à la validation du nom reçu et initialisation de la propriété nom
     * du bean
     */
    private void traiterNom( String name, Group group ) {
        try {
            validationNom( name );
        } catch ( FormValidationException e ) {
        	System.out.println("mauvais nom.");
            setErreur( "nameGroup", e.getMessage() );
        }
    }
    /* Validation du nom */
    private void validationNom( String nameGroup ) throws FormValidationException {
        if ( nameGroup != null && nameGroup.length() < 3 ) {
        	System.out.println( "Le nom d'utilisateur doit contenir au moins 3 caractères.");
            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
        
//        TODO checker si le nom exist pas ds la bdd
//        else if (){ 
//        	
//        }
    }
    
    /*
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     */
    private void traiterEmail( String email, Person person ) {
        try {
        	System.out.println("Email: "+ email);
            validationEmail( email );
        } catch ( FormValidationException e ) {
        	System.out.println("mauvaise adresse mail");
            setErreur( "email", e.getMessage() );
        }
    }

    /* Validation de l'adresse email */
    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                System.out.println( "Merci de saisir une adresse mail valide." );

                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
//            } else if ( groupDao.trouver( email ) != null ) {
//            	System.out.println("Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
//                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } 
    }
    
    
    
    
    /*
     * Appel à la validation des mots de passe reçus, chiffrement du mot de
     * passe et initialisation de la propriété motDePasse du bean
     */
    private String traiterMotsDePasse( String pwd, String confirmation) {
        try {
            validationMotsDePasse( pwd, confirmation );
        } catch ( FormValidationException e ) {
        	System.out.println("mauvais mdp");

            setErreur( "pwd", e.getMessage() );
        }
        catch (SecondException e){

            setErreur( "confirmPwd", e.getMessage() );
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
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String pwdChiffre = passwordEncryptor.encryptPassword( pwd );

        return pwdChiffre;
    }

   
    

   

    /* Validation des mots de passe */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException, SecondException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
            	System.out.println("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
                throw new SecondException( "Password different from the first" );
            } else if ( motDePasse.length() < 3 ) {            	
            	System.out.println("Les mots de passe doivent contenir au moins 3 caractères.");
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } 
//        TODO verifier que le pwd est unique dans le group
//        else if(){
//        	
//        }
        
        else {
        	System.out.println("Merci de saisir et confirmer votre mot de passe.");
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

  

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
