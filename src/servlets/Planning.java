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
import dao.DoodleDao;
import dao.ExpensesDao;
import dao.GroupDao;
import dao.PersonDao;
import dao.ThingsDao;
import beans.ColDoodle;
import beans.Doodle;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.Things;
import forms.ConnexionForm;
import forms.DoodleForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.PersonForm;
import forms.RegistrationForm;
import forms.ThingsForm;

public class Planning extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

    private GroupDao	groupDAO;
    private PersonDao 	personDAO;
    private ExpensesDao expensesDAO;
    private DoodleDao doodleDAO;
    private ThingsDao thingsDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
        this.expensesDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpensesDao();
        this.doodleDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDoodleDao();
        this.thingsDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getThingsDao();

    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
		     if(request.getParameter("action").equalsIgnoreCase(("createPlanning"))){
		    	 String startDate = request.getParameter("startDate");
		    	 System.out.println( "ACTION"+startDate);
		    	 request.setAttribute("planningCreated", "true");
		    	 request.setAttribute("startDate", request.getParameter("startDate"));
		    	 request.setAttribute("endDate", request.getParameter("endDate"));
		 		this.getServletContext().getRequestDispatcher("/index.jsp?page=planningMeals").forward(request, response);

		     }
		}

    }
	
}