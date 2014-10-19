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
import dao.DAOException;
import dao.DAOFactory;
import dao.GroupDao;
import dao.PersonDao;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.ContactListForm;
import forms.RegistrationForm;

public class PersoArea extends HttpServlet {
	
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
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
		this.getServletContext().getRequestDispatcher("/persoArea").forward(request, response);
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
		// Si l'action correspondante est modification de name
		else if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("modifyName")){
			modifyName(request, response);
			
		}
		// Si l'action correspondante est modification d'email
		else if (request.getParameter("action") != null && request.getParameter("action")
				.equalsIgnoreCase("modifyEmail")) {
			modifyEmail(request, response);

		}
		// Si l'action correspondante est modification d'email
		else if (request.getParameter("action") != null && request.getParameter("action")
				.equalsIgnoreCase("modifyPwd")) {
			modifyPwd(request, response);

		}
		// Affichage normal de personalArea. A mettre dans une fonction à part
		else if(request.getParameter("action") != null && request.getParameter("action")
				.equalsIgnoreCase("display")) {
			// recuperer liste des groupes de la personne
			ConnexionForm form = new ConnexionForm(personDAO);

			ArrayList<Group> listGroups = form.getGroups(request);
			String error = form.getError();
			if (error == null) {

		    	HttpSession session = request.getSession(); 
				session.setAttribute("listGroups", listGroups);
				//request.setAttribute("listGroups", listGroups);
				
				this.getServletContext().getRequestDispatcher("/personalArea.jsp").forward(request, response);

			} else {
				// traiter l'erreur et afficher un beau message sur le site
			}
		}
		else if(request.getParameter("action") != null && request.getParameter("action")
				.equalsIgnoreCase("addExistingPerson")) {
			
			HttpSession session = request.getSession(); 
	        ArrayList<Person> contactList = (ArrayList<Person>) session.getAttribute("contactList");
			ContactListForm contactListForm = new ContactListForm(contactListDao);
	        contactListForm.addExistingPerson(request, contactList);
	        
	        session.setAttribute("contactList", contactList);

	        Map<String, String> errors = contactListForm.getErrors();
	        String errorStr = errors.get("addExistingPerson");

            request.setAttribute("error", errorStr);
	        this.getServletContext().getRequestDispatcher( "/persoArea?action=display" ).forward( request, response );  
		}
		else if(request.getParameter("action") != null && request.getParameter("action")
				.equalsIgnoreCase("deletePersonList")) {

	        
	        // enregistrement de la personne pour la section
	        HttpSession session = request.getSession(); 
	        ArrayList<Person> contactList = (ArrayList<Person>) session.getAttribute("contactList");
			ContactListForm contactListForm = new ContactListForm(contactListDao);
			//on modifie contactList dans le fonction deletePersonList direct
	        contactListForm.deletePersonList(request, contactList);
	        
	        session.setAttribute("contactList", contactList);
	        
	        this.getServletContext().getRequestDispatcher( "/persoArea?action=display" ).forward( request, response ); 
		}
//		else if(request.getParameter("action") != null && request.getParameter("action")
//				.equalsIgnoreCase("addContactIntoGroup")) {
//			
//			HttpSession session = request.getSession(); 
//	        ArrayList<Person> contactList = (ArrayList<Person>) session.getAttribute("contactList");
//	        Group group = (Group) session.getAttribute("group");
//
//			ContactListForm contactListForm = new ContactListForm(contactListDao, groupDAO);
//
//	        contactListForm.addContactIntoGroup(request, contactList, group);
//	        session.setAttribute("group", group);
//	        
//	        this.getServletContext().getRequestDispatcher( "/persoArea?action=display" ).forward( request, response ); 
//		
//		}
		

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void modifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RegistrationForm form = new RegistrationForm(personDAO);
		Person person = form.modifyLogin(request);
		
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyLogin");
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
	}
	
	private void modifyName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RegistrationForm form = new RegistrationForm(personDAO);
		Person person = form.modifyName(request);
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(! errors.containsKey("modifyName")){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyName");
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
	}
	
	private void modifyEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RegistrationForm form = new RegistrationForm(personDAO);
		Person person = form.modifyEmail(request);
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyEmail");
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
	}
	
	private void modifyPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RegistrationForm form = new RegistrationForm(personDAO);
		Person person = form.modifyPwd(request);
		
		HttpSession session = request.getSession();
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			session.setAttribute("person", person);
			request.setAttribute("actionDone", "modifyPwd");
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/persoArea?action=display").forward(request, response);
		}
	}
	
}
