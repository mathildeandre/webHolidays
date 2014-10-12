package forms;

import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.RowExpenses;
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
    
    /** c'est ici que l'on va recuperer chaque ligne du tab et les info de checkbox etc
     * afin de faire uniquement des insert/update dans la dao
     */
    public Expenses saveTab(HttpServletRequest request, Group group){
    	Expenses expenses = new Expenses();
        ArrayList<RowExpenses> listRowExpenses = new ArrayList<RowExpenses>();
    	
    	 int nbLines = Integer.parseInt(request.getParameter("nbLineHidden"));
    	 int nbPersons = Integer.parseInt(request.getParameter("nbMemberHidden"));
    	 Long idGroup = group.getId();
    try {
    		 
    	 //System.out.println("NB LIGNEEEES : "+nbLines);
    	 //traitement de chaque ligne
    	 for(int i=0; i<nbLines; i++){
    		 RowExpenses rowE = new RowExpenses();

        	 int idRow = Integer.parseInt(request.getParameter(i+"idRow"));

        	 String idNameBuyer = request.getParameter(i+"select");
        	 
        	 String[] infoBuyer = idNameBuyer.split("-");
        	 int idBuyer = Integer.parseInt(infoBuyer[0]); // id
        	 String nameBuyer = infoBuyer[1]; // name
        	 
        	 int amount = Integer.parseInt(request.getParameter(i+"total"));
        	 /* TODO il faudra gerer les caractere non compatibles (accents..etc) */
        	 String descript = request.getParameter(i+"descript");
        	 
        	 
        	 if(idRow == -1){ /* la row existait pas dans la base */
        		 idRow = expensesDao.createNewRow(idBuyer, amount, descript, idGroup);
        		 //TODO createNewRow a du etre modifier en mode static ... =( a revoir
        		 
        	 }
        	 else{ /* la row existait deja */
        		 expensesDao.updateRow(idRow, idBuyer, amount, descript);
        		 expensesDao.deleteBeneficiaries(idRow);
        	 }

        	 Person buyer = new Person();
        	 buyer.setId((long) idBuyer);
        	 buyer.setName(nameBuyer);
        	 
        	 rowE.setId((long) idRow);
        	 rowE.setBuyer(buyer);
        	 rowE.setAmount(amount);
        	 rowE.setDescription(descript);
        	 
        	 HashMap<Long, String> mapCheckBox = new HashMap<Long, String>();
        	 
        	 //traitement de chaque personnes
        	 for(int j=0; j<nbPersons; j++){
        		 int idPerson = Integer.parseInt(request.getParameter("th"+j));
            	 String isChecked = request.getParameter(""+i+j);
            	 if (isChecked == null){
            		 //System.out.println("ligne : "+i+", person : "+j+" NA PAS coché");
            	 }
            	 else if (isChecked.equalsIgnoreCase("on")){
            		 //System.out.println("ligne : "+i+", person : "+j+" A coché =DD ");
            		 mapCheckBox.put((long) idPerson, "checked");
            		 expensesDao.insertIntoBeneficiaries(idRow, idPerson);
            	 }
        		 
        	 }

        	 //System.out.println("ID ROWWW : "+idRow+ "-- idBuyer : "+idBuyer+ "-- amount : "+amount+"-- descript"+descript );

        	 rowE.setMapCheckBox(mapCheckBox);
        	 listRowExpenses.add(rowE);
    		 
    	 }

         expenses.setListRowExpenses(listRowExpenses);
    	 
        
    	} catch ( DAOException e ) {
             errors.put("getExpenses", "Échec de la sauvegarde du TAB : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
         }

         return expenses;
    }
    
    public Map<String, String> getErrors() {
        return errors;
    }
}
