package servlets;


import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.ExpensesDao;
import dao.GroupDao;
import dao.PersonDao;
import dao.ThingsDao;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.Things;
import forms.ConnexionForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.RegistrationForm;
import forms.ThingsForm;

public class ThingsServlet extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

    private ThingsDao thingsDAO;

    public void init() throws ServletException {
        this.thingsDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getThingsDao();
       
    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if(request.getParameter("action") != null){
			
	    	   	if(request.getParameter("action").equalsIgnoreCase("addPersonalThing")){
	    		   addPersonalThing(request, response);
	    	   	}

	    	   	else if(request.getParameter("action").equalsIgnoreCase("addGroupThing")){
	    		   addGroupThing(request, response);
	    	   	}
	    	   	else if(request.getParameter("action").equalsIgnoreCase("changeGroupThing")){
	    		   modifySelectGroupThing(request, response);
	       		}
			   	else if(request.getParameter("action").equalsIgnoreCase("deletePersonalThing")){
		    		   deletePersonalThing(request, response);
		   		}
			   	else if(request.getParameter("action").equalsIgnoreCase("deleteGroupThing")){
		    		   deleteGroupThing(request, response);
				}
		}
		

    }
	private void deletePersonalThing(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
	    
	    Things things = (Things) session.getAttribute("things");
		ThingsForm form = new ThingsForm(thingsDAO);
	    
		form.deletePersonalThing(request, things);
		
		Map<String, String> errors = form.getErrors();
    	if(errors.isEmpty()){
			//request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);

		}
	}
	private void deleteGroupThing(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		HttpSession session = request.getSession(); 
	    
	    Things things = (Things) session.getAttribute("things");
		ThingsForm form = new ThingsForm(thingsDAO);
		
		
	    
		form.deleteGroupThing(request, things);
		
		
		
		
		Map<String, String> errors = form.getErrors();
    	if(errors.isEmpty()){
			//request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);

		}
	}
	private void modifySelectGroupThing(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		HttpSession session = request.getSession(); 
	    
	    Group group = (Group) session.getAttribute("group");
	    Things things = (Things) session.getAttribute("things");
		ThingsForm form = new ThingsForm(thingsDAO);
	    
		
		
	    
		Things newThings = form.modifySelectGroupThing(request, things, group);
		session.setAttribute("things", newThings);
		
		Map<String, String> errors = form.getErrors();
    	if(errors.isEmpty()){
			//request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);

		}
	}
	
	private void addPersonalThing(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		System.out.println("OUHHHHH  2 :"+request.getParameter("inputPersonalThing"));

	    HttpSession session = request.getSession(); 
	    
	    Group group = (Group) session.getAttribute("group");

		Things things = (Things) session.getAttribute("things");
		ThingsForm form = new ThingsForm(thingsDAO);
	    
		Things newThings = form.addPersonalThing(request, things, group);
		session.setAttribute("things", newThings);
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			//request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);

		}
	}
	
private void addGroupThing(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	System.out.println("OUHHHHH  2 group :"+request.getParameter("inputGroupThing"));
	
	HttpSession session = request.getSession(); 
    
    Group group = (Group) session.getAttribute("group");

	Things things = (Things) session.getAttribute("things");
	ThingsForm form = new ThingsForm(thingsDAO);
    
	Things newThings = form.addGroupThing(request, things, group);
	session.setAttribute("things", newThings);
	
	Map<String, String> errors = form.getErrors();
	//la modif de login s'est bien passée
	if(errors.isEmpty()){
		//request.setAttribute("createPerson", "The person has been add to the members of the group !");
		this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
	}
	//la modif n'a pas marche
	else{
		//erreur a traitée dans personalArea
		request.setAttribute("errors", errors);
		this.getServletContext().getRequestDispatcher("/index.jsp?page=things").forward(request, response);
	}
	
	}
	
}

