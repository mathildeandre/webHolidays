package forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        
        HttpSession session = request.getSession(); 
        //request.setAttribute("errors", form.getErrors());
        Person person = (Person) session.getAttribute("person");
        
        long idPers = person.getId();

        Group group = new Group();
        try {

            //traiterPseudo( pseudoAdmin );
        	group.setName(nameG);
        	long idGroup = groupDao.createGroup(group);

            if ( idGroup != 0 ) {
                System.out.println( "Succès de l'ajout de groupe.");
            	group.setId(idGroup);
            	
            	boolean insertBelongTo = groupDao.registerGroup(idPers, idGroup);
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
