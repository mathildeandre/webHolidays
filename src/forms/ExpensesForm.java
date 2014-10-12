package forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Expenses;
import beans.Group;
import beans.Person;
import dao.DAOException;
import dao.ExpensesDao;
import dao.GroupDao;

public class ExpensesForm extends Exception {

    private ExpensesDao expensesDao;
    private Map<String, String> errors;
    
    
    public ExpensesForm(ExpensesDao expensesDao) {
        this.expensesDao = expensesDao;
        errors = new HashMap<String, String>();
    }
    
    
    public Expenses getExpenses(HttpServletRequest request, Group group){
    	Expenses expenses = new Expenses();
         try {
         	expensesDao.getExpenses(group, expenses); /* on rempli dans le dao lobjet expenses */
         } catch ( DAOException e ) {
             errors.put("getExpenses", "Échec de la selection d'expenses : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
             return null;
         }
    	return expenses;
    }
    
    public void saveTab(HttpServletRequest request){
    	Expenses expenses = new Expenses();
         try {
         	expensesDao.saveTab(expenses); /* on rempli dans le dao lobjet expenses */
         } catch ( DAOException e ) {
             errors.put("getExpenses", "Échec de la sauvegarde du TAB : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
         }
    }
    
    public Map<String, String> getErrors() {
        return errors;
    }
}
