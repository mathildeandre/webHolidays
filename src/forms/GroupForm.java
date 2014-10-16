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

public final class GroupForm {

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> errors          = new HashMap<String, String>();
    private GroupDao     groupDao;
    private PersonDao     personDao;

    public GroupForm(GroupDao groupDao ) {
        this.groupDao = groupDao;
    }
    public GroupForm(PersonDao personDao ) {
        this.personDao = personDao;
    }

    public Map<String, String> getErrors() {
        return errors;
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
        	boolean nameIsNew = groupDao.checkGroup(nameG);
        	if(!nameIsNew){
        		errors.put("createGroup", "The name of the group already exists");
        	}
        	if(errors.isEmpty()){
                group.setIdAdmin((int) idPers);
        		group.setName(nameG);
        		long idGroup = groupDao.createGroup(group, idPers);
        		if ( idGroup != 0 ) {
        			System.out.println( "Succès de l'ajout de groupe.");
        			group.setId(idGroup);
            	
        			groupDao.registerGroup(idPers, idGroup, 1);
        		} else {
            	 System.out.println( "Échec de l'inscription.");
        		}
        	}
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return group;
    }
    
    
    public ArrayList<Person> getMembers(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Group group = (Group) session.getAttribute("group");
		ArrayList<Person> listMembers = new ArrayList<Person>();

		try {
			listMembers = groupDao.getMembers(group);
			return listMembers;
		} catch (DAOException e) {
			errors.put(
					"getContact",
					"Échec de la selection des contacts: une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
			e.printStackTrace();
			return null;
		}
	}
    
	public ArrayList<Person> getContacts(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		ArrayList<Person> listContacts = new ArrayList<Person>();

		try {
			listContacts = personDao.getContacts(person);
			return listContacts;
		} catch (DAOException e) {
			errors.put(
					"getContact",
					"Échec de la selection des contacts: une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
			e.printStackTrace();
			return null;
		}
	}

   
}
