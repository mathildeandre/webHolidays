package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Group;
import dao.DAOFactory;
import dao.GroupDao;
import forms.ConnexionForm;

public class Connexion extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ERROR        = "error";
    public static final String VUE              = "/WEB-INF/welcome.jsp";

    private GroupDao     groupDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.groupDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getGroupDao();
    }

    
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	//	String nameGroup = request.getParameter("coGroup");
	//	String pwdGroup = request.getParameter("coPwd");
		
		ConnexionForm connexionForm = new ConnexionForm(groupDAO);
		Group myGroup = connexionForm.connectUser(request);
		
//		connexionForm.getErreurs();
		
   //     String result = groupDAO.findGroup(nameGroup, pwdGroup);

//        /* Stockage du formulaire et du bean dans l'objet request */
//        request.setAttribute( "result", result );
//        request.setAttribute( "nameGroup", nameGroup );

//        if(result.equalsIgnoreCase("OK_admin")){
//            this.getServletContext().getRequestDispatcher("/index.jsp?page=homepage").forward(request, response);
//        }else if(result.equalsIgnoreCase("OK_member")){
//            this.getServletContext().getRequestDispatcher("/index.jsp?page=homepage").forward(request, response);
//        }else{
//            this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
//        }

    }
	
}