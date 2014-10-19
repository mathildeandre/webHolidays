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

public class PersonForm {


    private String              resultat;
    private String error;
    private Map<String, String> errors          = new HashMap<String, String>();
    private PersonDao     personDao;
    private GroupDao      groupDao;
    
	public PersonForm( PersonDao personDao) {
        this.personDao = personDao;
        error = null;
    }
	public PersonForm( PersonDao personDao, GroupDao groupDao) {
        this.personDao = personDao;
        this.groupDao = groupDao;
        error = null;
    }
	
	public Person searchPerson(Group group, String login){

		    boolean isAlreadyInListMembers = group.containsMember(login);
		    if(isAlreadyInListMembers){
		    	errors.put("searchPerson", "the person is already in your list of members");
		    	return null;
		    }else{

		    	Person person = new Person();
		    	try {
		    		boolean personExists = personDao.findUser(person, login);

		    		if (personExists) {
		    			groupDao.registerGroup(person.getId(), group.getId(), 0);
		    			group.addPersonIntoListMembers(person);
		    			System.out.println("Succès de la recherche.");
		    			return person;
		    		} else {
		    			System.out.println("Échec de la recherche de login.");
		    			errors.put("searchPerson", "the person you're looking for doesn't exist");
		    			return null;
		    		}
		    	} catch (DAOException e) {
		    		errors.put("searchPerson","Échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
		    		e.printStackTrace();
		    	}
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

