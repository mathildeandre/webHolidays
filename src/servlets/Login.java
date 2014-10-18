package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ContactListDao;
import dao.DAOFactory;
import dao.GroupDao;
import dao.PersonDao;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.ContactListForm;
import forms.RegistrationForm;

public class Login extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

    private GroupDao     groupDAO;
    private PersonDao     personDAO;
	private ContactListDao contactListDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
        this.contactListDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getContactListDao();
       
    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm(personDAO);

        /* Traitement de la requête et récupération du bean en résultant */
        Person person = form.connectUser( request );
        Map<String, String> errors = form.getErrors();
        
        /* generer list Contact qui contient des persons avec comme info :
         * id, name, login, email*/
        ContactListForm contactListForm = new ContactListForm(contactListDao);
        ArrayList<Person> contactList = contactListForm.getContactList(person);
        
        // enregistrement de la personne pour la section
        HttpSession session = request.getSession(); 
        session.setAttribute("person", person);
        session.setAttribute("contactList", contactList);
      

        if(errors.isEmpty()){
            this.getServletContext().getRequestDispatcher( "/persoArea?action=display" ).forward( request, response );
        }
        else{
        	//TODO gerer les erreurs
            request.setAttribute("errors", errors);
            this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
        }

    }
	
}
