package forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.GroupDao;
import dao.PersonDao;

public final class GroupForm {

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private GroupDao     groupDao;

    public GroupForm(GroupDao groupDao ) {
        this.groupDao = groupDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Group createGroup( HttpServletRequest request ) {
    	
        String nameG = request.getParameter("groupName" );

        Group group = new Group();
        try {

            //traiterPseudo( pseudoAdmin );
        	group.setName(nameG);
        	long idGroup = groupDao.createGroup(group);
        	group.setId(idGroup);

            if ( idGroup != 0 ) {
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

   
}
