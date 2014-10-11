package forms;

import java.util.ArrayList;
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

public class ConnexionForm {


    private String              resultat;
    private String error;
    private Map<String, String> errors          = new HashMap<String, String>();
    private PersonDao     personDao;
    private GroupDao      groupDao;
    
	public ConnexionForm( PersonDao personDao) {
        this.personDao = personDao;
        error = null;
    }
	public ConnexionForm( PersonDao personDao, GroupDao groupDao) {
        this.personDao = personDao;
        this.groupDao = groupDao;
        error = null;
    }
	
	public Person connectUser(HttpServletRequest request){
		    String login = request.getParameter("coLogin");
	        String pwd = request.getParameter("coPwd" );

	        Person person = new Person();
	        try {
	       
	        	boolean success = personDao.checkUser(person,login, pwd);

	            if ( success ) {
	                System.out.println( "Succès de la connexion.");
	            	return person;
	            } else {
	            	 System.out.println( "Échec de la connexion.");
	            	 errors.put("signIn", "wrong pwd or login");
	            	 return null;
	            }
	        } catch ( DAOException e ) {
	            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	            return null;
	        }
	}
	
	public Group connectGroup(HttpServletRequest request){
	    String nameGroup = request.getParameter("nameGroup");
	    HttpSession session = request.getSession();
	    Person person = (Person) session.getAttribute("person");

        Group group = new Group();
        try {
        	long idGroup = personDao.findGroup(nameGroup, person);
        	group.setName(nameGroup);
        	group.setId(idGroup);
        	
        	ArrayList<Person> listMembers = groupDao.getMembers(group);
            group.setListMembers(listMembers);
            
        } catch ( DAOException e ) {
            errors.put("connectGroup", "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
            e.printStackTrace();
            return null;
        }
        return group;
}
	
	public ArrayList<Group> getGroups(HttpServletRequest request){

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		ArrayList<Group> listGroups = new ArrayList<Group>();

        try {
       
        	listGroups = (ArrayList<Group>) personDao.getGroups(person).clone();
        	return listGroups;
        } catch ( DAOException e ) {
            error = "Échec de la selection des groupes: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
            return null;
        }
}

	
	public Map<String, String> getErrors() {
        return errors;
    }

    public String getResultat() {
        return resultat;
    }
    public String getError() {
        return error;
    }
}

