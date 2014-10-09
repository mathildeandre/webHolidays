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

public final class PersoForm {

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              error;
    private Map<String, String> errors          = new HashMap<String, String>();
    private PersonDao     personDao;

    public PersoForm(PersonDao personDao ) {
        this.personDao = personDao;
        error = "";
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getError() {
        return error;
    }

    public Person modifyLogin(HttpServletRequest request) {
    	
        String login = request.getParameter("login" );
        
        HttpSession session = request.getSession(); 
        Person person = (Person) session.getAttribute("person");
        

        try {
        	System.out.println("changement de login reussi !");
        	personDao.modifyLogin(person);
        	person.setLogin(login);
            return person;
        } catch ( DAOException e ) {
            errors.put("modifyLogin", "Fail of the modification of login : thank you to try egain in few minutes.");
            e.printStackTrace();
            return null;
        }
    }

   
}
