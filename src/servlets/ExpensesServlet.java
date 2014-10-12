package servlets;


import java.io.IOException;
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
import beans.Expenses;
import beans.Group;
import beans.Person;
import forms.ConnexionForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.RegistrationForm;

public class ExpensesServlet extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

//    private GroupDao     groupDAO;
//    private PersonDao     personDAO;
    private ExpensesDao expensesDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
//        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
//        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
        this.expensesDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpensesDao();
       
    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		System.out.println("OUHHHHHHHHHHHHHHHHHHHHHH"+request.getParameter("expenseOf"));
		
		
		if(request.getParameter("action") != null){
	    	   if(request.getParameter("action").equalsIgnoreCase("saveTab")){
	    		   saveTab(request, response);
	    	   }
//	    	   else if(request.getParameter("action").equalsIgnoreCase("display")){
//	    		   displayGroup(request, response);
//	    		
//	    	   }
	       }

    }
	
	private void saveTab(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		

	    HttpSession session = request.getSession(); 
	    Group group = (Group) session.getAttribute("group");
		ExpensesForm form = new ExpensesForm(expensesDAO);
		
		Expenses expenses = form.saveTab(request, group);
		session.setAttribute("expenses", expenses);
		
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			//request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=expenses").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=expenses").forward(request, response);

		}
	}
	
}

