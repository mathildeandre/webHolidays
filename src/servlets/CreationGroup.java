package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.GroupDao;
import dao.PersonDao;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.GroupForm;

public class CreationGroup extends HttpServlet {
	
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

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        GroupForm form = new GroupForm(groupDAO);

        /* Traitement de la requête et récupération du bean en résultant */
        Group group = form.createGroup(request );

    	request.setAttribute("group", group);

        if(group != null){
            this.getServletContext().getRequestDispatcher( "/personalArea.jsp" ).forward( request, response );
        }
        else{
        	//TODO gerer les erreurs
            request.setAttribute("errorConnexion", "error");
            this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
        }

    }
	
}

