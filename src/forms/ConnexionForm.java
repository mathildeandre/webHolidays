package forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.GroupDao;
import dao.PersonDao;

public class ConnexionForm {


    private String resultat;
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
	    int idGroup = Integer.parseInt(request.getParameter("idGroup"));
	    HttpSession session = request.getSession();
	    Person person = (Person) session.getAttribute("person");

        try {
        	//idPerson permet de verifier si ce groupe est accessible depuis cett person
        	Group group = groupDao.getGroupAndVERIF(idGroup, person.getId());
        	if(group == null){
        		errors.put("connectGroup", "Usurpation de group : la person n'a pas acces a ce group");
        		return null;
        	}
        	ArrayList<Person> listMembers = groupDao.getMembers(group);
            group.setListMembers(listMembers);
            
            return group;
            
        } catch ( DAOException e ) {
            errors.put("connectGroup", "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
            e.printStackTrace();
            return null;
        }
}
	
	public ArrayList<Group> getGroups(HttpServletRequest request){

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		ArrayList<Group> listGroups = new ArrayList<Group>();

        try {
       
        	listGroups = personDao.getGroups(person);
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

