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

    public int hasRight(Long idGroup, Long idPerson){
    	int hasR = 0;
    	try {
        	hasR = groupDao.hasRight(idGroup,idPerson);
        	System.out.println("has right ???????????????,"+ hasR);
        	
		} catch (DAOException e) {
			errors.put("addRights", "Échec dans l'ajout de droit..");
			e.printStackTrace();
		}
    	
    	return hasR;
    }
    
    public void addRights(Long idGroup, int idPersonNewRights){
    	try {
			groupDao.updateRights(1, idGroup,idPersonNewRights);
		} catch (DAOException e) {
			errors.put("addRights", "Échec dans l'ajout de droit..");
			e.printStackTrace();
		}
    }
    public void removeRights(Long idGroup, int idPersonNewRights){
    	try {
			groupDao.updateRights(0, idGroup,idPersonNewRights);
		} catch (DAOException e) {
			errors.put("addRights", "Échec dans l'ajout de droit..");
			e.printStackTrace();
		}
    }
    public Group createGroup( HttpServletRequest request ) {
    	
        String nameG = request.getParameter("groupName" );
        
        HttpSession session = request.getSession(); 
        //request.setAttribute("errors", form.getErrors());
        Person person = (Person) session.getAttribute("person");
        
        long idPers = person.getId();
        String loginPerson = person.getLogin();
        Group group = new Group();
        try {

            //traiterPseudo( pseudoAdmin );
			
	    	ArrayList<Group> listGroups =  (ArrayList<Group>) session.getAttribute("listGroups");
        	//ArrayList<Group> listGroups = (ArrayList<Group>) request.getAttribute("listGroups");
        	
        	boolean nameIsNew = true;//groupDao.checkGroup(nameG);
        	System.out.println("name demandé :"+nameG);
        	for (Group grp : listGroups){

            	System.out.println("list names :"+grp.getName());
        		if(grp.getName().equalsIgnoreCase(nameG)){
        			nameIsNew = false;
        		}
        		
        	
        	}
        	if(!nameIsNew){
        		errors.put("createGroup", "The name of the group already exists");
        	}
        	if(errors.isEmpty()){
                group.setLoginAdmin(loginPerson);
        		group.setName(nameG);
        		long idGroup = groupDao.createGroup(group, loginPerson);
        		if ( idGroup != 0 ) {
        			System.out.println( "Succès de l'ajout de groupe.");
        			group.setId(idGroup);
            	
        			groupDao.registerGroup(idPers, idGroup, 1);
        		} else {
            	 System.out.println( "Échec de l'inscription.");
        		}
        	}
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, "
            		+ "merci de réessayer dans quelques instants.";
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
					"Échec de la selection des contacts: une erreur imprévue est survenue, "
					+ "merci de réessayer dans quelques instants.");
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
					"Échec de la selection des contacts: une erreur imprévue est survenue, "
					+ "merci de réessayer dans quelques instants.");
			e.printStackTrace();
			return null;
		}
	}

   
}
