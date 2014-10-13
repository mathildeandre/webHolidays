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
import beans.Doodle;
import beans.Expenses;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.DoodleForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.RegistrationForm;

public class DoodleServlet extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

//    private GroupDao     groupDAO;
//    private PersonDao     personDAO;
    private DoodleDao doodleDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
//        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
//        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
        this.doodleDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDoodleDao();
       
    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {		
		
		if(request.getParameter("action") != null){
	    	   if(request.getParameter("action").equalsIgnoreCase("createDoodle")){
	    		   createDoodle(request, response);
	    	   }
	    	   else if(request.getParameter("action").equalsIgnoreCase("displayDoodle")){
	    		   displayDoodle(request, response);
	    		
	    	   }
	       }

    }
	
	private void createDoodle(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		// on recup le groupe
	    HttpSession session = request.getSession(); 
	    Group group = (Group) session.getAttribute("group");
	    
	    // on recup les doodle
	    ArrayList<Doodle> listDoodles = (ArrayList<Doodle>) session.getAttribute("doodles");
	    
		DoodleForm form = new DoodleForm(doodleDAO);
		// on cre le doodle
		Doodle doodle = form.createDoodle(request, group);
		Map<String, String> errors = form.getErrors();
		
		//la creation du doodle s'est bien passe
		if(errors.isEmpty()){
			// on ajoute le doodle cree a la liste des doodles du groupe
			listDoodles.add(doodle);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=doodle").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=doodle").forward(request, response);

		}
	}
	
	private void displayDoodle(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		String nameDood = request.getParameter("idDoodle");
		HttpSession session = request.getSession();
		ArrayList<Doodle> listDoodles = (ArrayList<Doodle>) session.getAttribute("doodles");
		
		for(int i=0; i<listDoodles.size(); i++){
			Doodle doodle = listDoodles.get(i);
			if(doodle.getNameDoodle().equalsIgnoreCase(nameDood)){
				request.setAttribute("numDoodle", doodle.getIdDoodle());
			}
		}
			System.out.println("doodle : "+request.getAttribute("numDoodle"));
		
			this.getServletContext().getRequestDispatcher("/index.jsp?page=doodle").forward(request, response);

	}
	
}

