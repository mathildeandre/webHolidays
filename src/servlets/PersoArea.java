package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
import forms.PersoForm;
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
     * @throws IOException 
     * @throws ServletException 
     */
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
      
		// Si l'action correspondante est modification de login
		if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("modifyLogin")){
			modifyLogin(request, response);
		}
		// Si l'action correspondante est modification de login
		else if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("modifyName")){
			modifyName(request, response);
			
		}
		// Affichage normal de personalArea. A mettre dans une fonction à part
		else{
			// recuperer liste des groupes de la personne
			ConnexionForm form = new ConnexionForm(personDAO);
			ArrayList<Group> listGroups = new ArrayList<>();
			// HttpSession session = request.getSession();
			// Person person = (Person) session.getAttribute("person");
			listGroups = form.getGroups(request);
			String error = form.getError();
			if (error == null) {
				request.setAttribute("listGroups", listGroups);
				this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);

			} else {
				// traiter l'erreur et afficher un beau message sur le site
			}
		}

    }
	
	
	private void modifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PersoForm form = new PersoForm(personDAO);
		Person person = form.modifyLogin(request);
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(! errors.containsKey("modifyLogin")){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyLogin");
			this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);
		}
	}
	
	private void modifyName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PersoForm form = new PersoForm(personDAO);
		Person person = form.modifyName(request);
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(! errors.containsKey("modifyLogin")){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyName");
			this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);
		}
	}
	
}
