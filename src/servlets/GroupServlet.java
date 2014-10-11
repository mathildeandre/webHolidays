package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
import forms.RegistrationForm;

public class GroupServlet extends HttpServlet {
	
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
		if(request.getParameter("action")!=null){

			 if(request.getParameter("action").equalsIgnoreCase("display")){
		        	doPost(request, response);
		     }else if(request.getParameter("action").startsWith(("connectGroup"))){
		    	 connectGroup(request, response);
		     }
		}
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       if(request.getParameter("action") != null){
    	   if(request.getParameter("action").equalsIgnoreCase("createPerson")){
    		   createPerson(request, response);
    	   }
    	   else if(request.getParameter("action").equalsIgnoreCase("display")){
    		   displayGroup(request, response);
    		
    	   }
       }

    }
	
	private void connectGroup(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//on est censé se retrouver la lorsque lon a clique sur un nom de groupe
   	 /* Préparation de l'objet formulaire */
       ConnexionForm form = new ConnexionForm(personDAO);
       HttpSession session = request.getSession(); 
       /* Traitement de la requête et récupération du bean en résultant */
       Group group = form.connectGroup(request);
       
       session.setAttribute("group", group);
       request.setAttribute("errors", form.getErrors());

       if(form.getErrors().isEmpty()){
           this.getServletContext().getRequestDispatcher( "/index.jsp?page=homepage" ).forward( request, response );
       }
       else{
       	//TODO gerer les erreurs
           this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
       }	
	}
	
	private void createPerson(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		RegistrationForm form = new RegistrationForm(personDAO, groupDAO);
		form.registerUserBySomeoneElse(request);
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/group?action=display").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/group?action=display").forward(request, response);

		}
	}
	
	private void displayGroup(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		// recuperer liste des groupes de la personne
		GroupForm form = new GroupForm(groupDAO);
		ArrayList<Person> listMembers= new ArrayList<>();
		listMembers = form.getMembers(request);
		Map<String, String> errors = form.getErrors();
		if (errors.isEmpty()) {
			request.setAttribute("listMembers", listMembers);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=group")
					.forward(request, response);

		} else {
			// traiter l'erreur et afficher un beau message sur le site
		}

	}
	
}
