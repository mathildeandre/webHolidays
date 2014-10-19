package servlets;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    
    String[] tabDays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}; 

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
		    	 request.setAttribute("planningCreated", "true");
		    	 
		    	 String date = request.getParameter("chooseStartDate");
		    	  SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		    	  Date dt1 = null;
		    	  String day = null;
					try {
						dt1 = format1.parse(date);
						int numDay = dt1.getDay();
						day = tabDays[numDay-1];
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	 

			     request.setAttribute("startDay", day);
		    	 request.setAttribute("startDate",date);
		    	 request.setAttribute("endDate", request.getParameter("chooseEndDate"));
		 		this.getServletContext().getRequestDispatcher("/index.jsp?page=planningMeals").forward(request, response);

		     }
		}

    }
	
}