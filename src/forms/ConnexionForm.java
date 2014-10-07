package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.PersonDao;

public class ConnexionForm {


    private String              resultat;
    private Map<String, String> errors          = new HashMap<String, String>();
    private PersonDao     personDao;
    
	public ConnexionForm( PersonDao personDao ) {
        this.personDao = personDao;
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
	            	 return null;
	            }
	        } catch ( DAOException e ) {
	            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
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
}
