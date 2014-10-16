package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import beans.RowExpenses;
import beans.Things;
import forms.ConnexionForm;
import forms.ExpensesForm;
import forms.GroupForm;
import forms.RegistrationForm;
import forms.ThingsForm;

public class DemoServlet extends HttpServlet {

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
			System.out.println("BIENVENUE DANS LA DEMO!");

			HttpSession session = request.getSession(); 

			session.setAttribute("displayErrorGroup", "You cannot add or create in the member section\n It is the DEMO");
			session.setAttribute("displayErrorExpenses", "You cannot save the tab\n It is the DEMO");
			session.setAttribute("displayErrorThings", "You cannot add in the things section\n It is the DEMO");
			
			if(request.getParameter("action") != null && request.getParameter("action").equals("init")){
		    	
				Group group = new Group();
				ArrayList<Person> listMembers = new ArrayList<>();
				for(int i=1; i<5; i++){
					Person person = new Person();
					person.setName("member"+i);
					person.setId((long) i);
					person.setLogin("loginMember"+i);
					listMembers.add(person);
				}
				group.setListMembers(listMembers);
				session.setAttribute("group", group);
				
				
				Expenses expenses = new Expenses();
				ArrayList<RowExpenses> listRowExpenses = new ArrayList<RowExpenses>();
				
				RowExpenses row1 = new RowExpenses();
				row1.setAmount(16);
				row1.setBuyer(listMembers.get(2));
				row1.setDescription("Glaces");
				row1.setId((long) 2);
				HashMap<Long,String> mapCheckBox = new HashMap<Long,String>();
				mapCheckBox.put((long) 2, "checked");
				mapCheckBox.put((long) 4, "checked");
				row1.setMapCheckBox(mapCheckBox);;
				
				RowExpenses row2 = new RowExpenses();
				row2.setAmount(48);
				row2.setBuyer(listMembers.get(3));
				row2.setDescription("Museum tickets");
				row2.setId((long) 3);
				HashMap<Long,String> mapCheckBox2 = new HashMap<Long,String>();
				mapCheckBox2.put((long) 1, "checked");
				mapCheckBox2.put((long) 2, "checked");
				mapCheckBox2.put((long) 3, "checked");
				mapCheckBox2.put((long) 4, "checked");
				row2.setMapCheckBox(mapCheckBox2);; 
					
				
				

				listRowExpenses.add(row1);
				listRowExpenses.add(row2);
				expenses.setListRowExpenses(listRowExpenses);
				
				session.setAttribute("expenses", expenses);
			}
			
			
			if(request.getParameter("action") != null && request.getParameter("action").equals("submit")){
				System.out.println("GERER LE DISPLAYYYY");
				
			}
		
		
		if(request.getParameter("page")!=null){
			String pageDemo = request.getParameter("page");
			this.getServletContext().getRequestDispatcher("/indexDemo.jsp?page="+pageDemo).forward(request, response);
			
			
		}
		else{
			this.getServletContext().getRequestDispatcher("/indexDemo.jsp?page=homepageDemo").forward(request, response);
		}
    }
	
	
}

