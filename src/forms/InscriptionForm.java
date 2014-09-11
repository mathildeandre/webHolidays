package forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import dao.DAOException;
import dao.GroupDao;

public final class InscriptionForm {
    private static final String CHAMP_EMAIL      = "email";
    private static final String CHAMP_PASS       = "motdepasse";
    private static final String CHAMP_CONF       = "confirmation";
    private static final String CHAMP_NOM        = "nom";

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private GroupDao      groupDao;

    public InscriptionForm( GroupDao groupDao ) {
        this.groupDao = groupDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Group inscrireUtilisateur( HttpServletRequest request ) {
        String name = getValeurChamp( request, "nameGroup" );
        String email = getValeurChamp( request, "email" );
        String pwdAdmin = getValeurChamp( request, "pwdAdmin" );
        String confirmPwdAdmin = getValeurChamp( request, "confirmPwdAdmin" );
        String pwdMembers = getValeurChamp( request, "pwdMembers" );
        String confirmPwdMembers = getValeurChamp( request, "confirmPwdMembers" );

        Group group = new Group();
        try {

            traiterNom( name, group ); 
            traiterEmail( email, group );
            String newPwdAdmin = traiterMotsDePasse( pwdAdmin, confirmPwdAdmin );
            group.setPwdAdmin(newPwdAdmin);
            String newPwdMembers = traiterMotsDePasse( pwdMembers, confirmPwdMembers);
            group.setPwdMembers(newPwdMembers);

            if ( erreurs.isEmpty() ) {
                groupDao.creer( group );
                System.out.println( "Succès de l'inscription.");
            } else {
            	 System.out.println( "Échec de l'inscription.");
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return group;
    }

    /*
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     */
    private void traiterEmail( String email, Group utilisateur ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
        	System.out.println("mauvaise adresse mail");
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );
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

            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
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

    /*
     * Appel à la validation du nom reçu et initialisation de la propriété nom
     * du bean
     */
    private void traiterNom( String name, Group group ) {
        try {
            validationNom( name );
        } catch ( FormValidationException e ) {
        	System.out.println("mauvais nom.");
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        group.setName( name );
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
        } else {
        	System.out.println("Merci de saisir une adresse mail.");
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    /* Validation des mots de passe */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
            	System.out.println("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {            	
            	System.out.println("Les mots de passe doivent contenir au moins 3 caractères.");
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
        	System.out.println("Merci de saisir et confirmer votre mot de passe.");
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /* Validation du nom */
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 3 ) {
        	System.out.println( "Le nom d'utilisateur doit contenir au moins 3 caractères.");
            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
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
