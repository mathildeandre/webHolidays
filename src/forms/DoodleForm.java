package forms;

import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Doodle;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.RowExpenses;
import dao.DAOException;
import dao.DoodleDao;
import dao.ExpensesDao;
import dao.GroupDao;

public class DoodleForm extends Exception {

    private DoodleDao doodleDao;
    private Map<String, String> errors;
    
    
    public DoodleForm(DoodleDao doodleDao) {
        this.doodleDao = doodleDao;
        errors = new HashMap<String, String>();
    }
    
    
    public Doodle createDoodle(HttpServletRequest request, Group group){
    	String name = request.getParameter("nameNewDoodle");
    	Doodle doodle = new Doodle();
         try {
         	long idDoodle = doodleDao.createDoodle(group, name); /* on rempli dans le dao lobjet expenses */
         	doodle.setIdDoodle(idDoodle);
         	doodle.setNameDoodle(name);
         } catch ( DAOException e ) {
             errors.put("createDoodle", "Échec de la creation du doodle : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
             return null;
         }
    	return doodle;
    }
    
    public ArrayList<Doodle> getDoodles(HttpServletRequest request, Group group){
    	ArrayList<Doodle> listDoodles = new ArrayList<Doodle>();
        try {
        	doodleDao.getDoodles(group, listDoodles); /* on rempli dans le dao lobjet expenses */
        } catch ( DAOException e ) {
            errors.put("getEDodles", "Échec de la selection des doodles : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
            e.printStackTrace();
            return null;
        }
   	return listDoodles;
    }
    
   
    public Map<String, String> getErrors() {
        return errors;
    }
}
