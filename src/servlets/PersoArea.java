package servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOException;
import dao.DAOFactory;
import dao.GroupDao;
import dao.PersonDao;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.RegistrationForm;

public class PersoArea extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

    private GroupDao     groupDAO;
    private PersonDao     personDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
       
    }
	
    /**
     * This method is called when the personalArea page has to be displayed. 
     * It will get all the groups and contacts matching the person connected. 
     * And then display them in the personalArea page
     */
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      
        // recuperer liste des groupes de la personne
		ConnexionForm form = new ConnexionForm(personDAO);
		ArrayList<Group> listGroups = new ArrayList<>();
		//HttpSession session = request.getSession();
		//Person person = (Person) session.getAttribute("person");
		listGroups = form.getGroups(request);
		String error = form.getError();
		if(error == null){
			request.setAttribute("listGroups", listGroups);
            this.getServletContext().getRequestDispatcher( "/personalArea.jsp" ).forward( request, response );
			
		}else{
			//traiter l'erreur et afficher un beau message sur le site
		}

    }
	
}
