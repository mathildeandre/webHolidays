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
import forms.ContactListForm;
import forms.DoodleForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.PersonForm;
import forms.RegistrationForm;
import forms.ThingsForm;

public class GroupServlet extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_GROUP         = "group";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "";

    private GroupDao	groupDAO;
    private PersonDao 	personDAO;
    private ExpensesDao expensesDAO;
    private DoodleDao doodleDAO;
    private ThingsDao thingsDAO;
	private ContactListDao contactListDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
        this.personDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPersonDao();
        this.expensesDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpensesDao();
        this.doodleDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDoodleDao();
        this.thingsDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getThingsDao();
        this.contactListDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getContactListDao();

    }

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if(request.getParameter("action")!=null){
		     if(request.getParameter("action").equalsIgnoreCase(("connectGroup"))){
		    	 connectGroup(request, response);
		     }
		}
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       if(request.getParameter("action") != null){
    	   Person personAdded = new Person();
    	   if(request.getParameter("action").equalsIgnoreCase("createPerson")){
    		   personAdded = createPerson(request, response);
    		   if(personAdded != null){
        		   addNewMemberInContactLists(request, response, personAdded);
    		   }
    	   }
    	   else if(request.getParameter("action").equalsIgnoreCase("searchPerson")){

    		   String login = request.getParameter("searchPerson");
    		   personAdded = searchPerson(request, response, login);
    		   if(personAdded != null){
        		   addNewMemberInContactLists(request, response, personAdded);
    		   }
    	   }
    	   else if(request.getParameter("action").equalsIgnoreCase("addContactIntoGroup")){

    		   String login = request.getParameter("groupListContacts");
    		   personAdded = searchPerson(request, response, login);
    		   
    		 //on ajoute le nouveau membre du groupe dans la list de contact de chaque membre du groupe
        	   if(personAdded != null){
        		   addNewMemberInContactLists(request, response, personAdded);
    		   }
    	   }
    	   else if(request.getParameter("action").equalsIgnoreCase("addHasRights")){
    		   	HttpSession session = request.getSession(); 
    		   	Group group = (Group) session.getAttribute("group");
    			int idPersonNewRights = Integer.parseInt(request.getParameter("addHasRights"));
    			GroupForm groupForm = new GroupForm(groupDAO);
    			groupForm.addRights(group.getId(), idPersonNewRights);
    			
    			this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);
    	   }
    	   else if(request.getParameter("action").equalsIgnoreCase("removeHasRights")){
	   		   	HttpSession session = request.getSession(); 
	   		   	Group group = (Group) session.getAttribute("group");
	   			int idPersonNewRights = Integer.parseInt(request.getParameter("removeHasRights"));
	   			GroupForm groupForm = new GroupForm(groupDAO);
	   			groupForm.removeRights(group.getId(), idPersonNewRights);

				this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);
   	   }
    	   
    	   
    	   
       }

    }
	

	private void addNewMemberInContactLists(HttpServletRequest request, HttpServletResponse response, Person personAdded ) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		Group group = (Group) session.getAttribute("group");
		ContactListForm contactListForm = new ContactListForm(contactListDao);
		contactListForm.addNewMemberInContactLists(group, personAdded);
		
	}
	
	
	private void connectGroup(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//on est censé se retrouver la lorsque lon a clique sur un nom de groupe
   	 /* Préparation de l'objet formulaire */
       ConnexionForm form = new ConnexionForm(personDAO,groupDAO);
       HttpSession session = request.getSession(); 
       /* Traitement de la requête et récupération du bean en résultant */
       Group group = form.connectGroup(request);
       
       session.setAttribute("group", group);
       
       // recuperation des doodle
       DoodleForm doodForm = new DoodleForm(doodleDAO);
       ArrayList<Doodle> listDoodles = doodForm.getDoodles(request, group);
       for(int i=0; i<listDoodles.size(); i++){
    	   ArrayList<ColDoodle> listCol = listDoodles.get(i).getListColDoodle();
    	   for(int j=0; j<listCol.size(); j++){
        	   System.out.println("nameChecked : "+listCol.get(j).getName());
    		   
    	   }
       }
       session.setAttribute("doodles", listDoodles);
       request.setAttribute("errorsDoodle", doodForm.getErrors());
       
       
       /* recuperation des expenses*/
       ExpensesForm expForm = new ExpensesForm(expensesDAO);
       Expenses expenses = expForm.getExpenses(request, group);
       session.setAttribute("expenses", expenses);
       request.setAttribute("errorsExpenses", expForm.getErrors());

       ThingsForm thingsForm = new ThingsForm(thingsDAO);
       Things things = thingsForm.getThings(request, group);
       session.setAttribute("things", things);
       request.setAttribute("errorsThings", thingsForm.getErrors());
       
       Person person = (Person) session.getAttribute("person");
       GroupForm groupForm = new GroupForm(groupDAO);
       int hasRight = groupForm.hasRight(group.getId(), person.getId());
       //hasRight sera 1 ou 0
       System.out.println("SECOND CHANCE has right ??? "+hasRight);
       session.setAttribute("hasRight", hasRight);
       
       int isAdmin = 0;
       if(group.getLoginAdmin().equals(person.getLogin())){ //cest que c ladmin
           isAdmin = 1;
       }
       session.setAttribute("isAdmin", isAdmin);
       
       

       if(form.getErrors().isEmpty()){
           this.getServletContext().getRequestDispatcher( "/index.jsp?page=homepage" ).forward( request, response );
       }
       else{
       	//TODO gerer les erreurs
           this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
       }	
	}
	
	private Person createPerson(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		RegistrationForm form = new RegistrationForm(personDAO, groupDAO);
		Person personAdded = form.registerUserBySomeoneElse(request);
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			request.setAttribute("createPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);

		}
		return personAdded;
	}
	
	private Person searchPerson(HttpServletRequest request, HttpServletResponse response, String login) throws ServletException, IOException {
		PersonForm form = new PersonForm(personDAO, groupDAO);


	    HttpSession session = request.getSession();
	    Group group = (Group) session.getAttribute("group");
	    
		Person personAdded = form.searchPerson(group, login);
		Map<String, String> errors = form.getErrors();
		//la modif de login s'est bien passée
		if(errors.isEmpty()){
			request.setAttribute("searchPerson", "The person has been add to the members of the group !");
			this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);
		}
		//la modif n'a pas marche
		else{
			//erreur a traitée dans personalArea
			request.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher("/index.jsp?page=group").forward(request, response);

		}

		return personAdded;
	}
	
//	private void displayGroup(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
//		
//		
//		// recuperer liste des groupes de la personne
//		GroupForm form = new GroupForm(groupDAO);
//		ArrayList<Person> listMembers= new ArrayList<>();
//		listMembers = form.getMembers(request);
//		Map<String, String> errors = form.getErrors();
//		if (errors.isEmpty()) {
//			request.setAttribute("listMembers", listMembers);
//			this.getServletContext().getRequestDispatcher("/index.jsp?page=group")
//					.forward(request, response);
//
//		} else {
//			// traiter l'erreur et afficher un beau message sur le site
//		}
//
//	}
	
}
