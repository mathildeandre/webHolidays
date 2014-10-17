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
import beans.ThingGroup;
import beans.ThingPersonal;
import beans.Things;
import dao.DAOException;
import dao.ExpensesDao;
import dao.GroupDao;
import dao.ThingsDao;

public class ThingsForm extends Exception {

    private ThingsDao thingsDao;
    private Map<String, String> errors;
    
    
    public ThingsForm(ThingsDao thingsDao) {
        this.thingsDao = thingsDao;
        errors = new HashMap<String, String>();
    }
    
    public Things getThings(HttpServletRequest request, Group group){
    	Things things = new Things();
    	try {

        	things = thingsDao.getThings(group);
         } catch ( DAOException e ) {
             errors.put("getThings", "Échec de la selection de personal thing : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
             return null;
         }
    	
    	
    	return things;
    }
    
    
    public Things modifySelectGroupThing(HttpServletRequest request, Things things, Group group){
    	String nameSelect = request.getParameter("nameSelect");
 	    String[] infoNameS = nameSelect.split("-");
 	    int idThingGroup = Integer.parseInt(infoNameS[0]);
 	    int idThingPerson = Integer.parseInt(request.getParameter(nameSelect));
     	//System.out.println(">>>>>>HELLLO BOYS 2 <<<<<<< , value select(id person) : "+idThingPerson+" idThing :"+idThingGroup);
 	    Long idGroup = group.getId();
 	    
 	   ArrayList<ThingGroup> listThingGroup = things.getListThingGroup();
 	   for(int i=0; i<listThingGroup.size(); i++){
 		   if(listThingGroup.get(i).getId() == idThingGroup){
 			  listThingGroup.get(i).setIdPerson(idThingPerson);
 		   }
 	   }
 	   
 	    things.setListThingGroup(listThingGroup);
 	   
 	    //TODO verifier que idThingPerson soit bien -1 ou parmi un des idMember du group
 	    //		verifier que idThingGroup possede sont id group correspond a sont group courant
 	   
 	    
 	    try {

       	thingsDao.updateGroupThings(idThingGroup, idThingPerson);
        } catch ( DAOException e ) {
            errors.put("getThings", "Échec de la selection de personal thing : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
            e.printStackTrace();
            return null;
        }
 	    
 	    return things;
    }
    
    public Things addPersonalThing(HttpServletRequest request, Things things, Group group){
    	
    	ThingPersonal thingPersonal = new ThingPersonal();
    	
    	String namePersoTh = request.getParameter("inputPersonalThing");

    	System.out.println("name thing : "+namePersoTh);
    	
    	thingPersonal.setName(namePersoTh);
    	
    	
	    Long idGroup = group.getId();
    	
    	try {

        	thingsDao.insertIntoPersonalThings(namePersoTh, idGroup);
         } catch ( DAOException e ) {
             errors.put("getThings", "Échec de la selection de personal thing : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
             return null;
         }
    	
    	things.addPersonalThing(thingPersonal);
    	
    	return things;
    }
    
    
    public Things addGroupThing(HttpServletRequest request, Things things, Group group){
    	ThingGroup thingGroup = new ThingGroup();
    	
    	String namePersoTh = request.getParameter("inputGroupThing");
    	System.out.println("name thing : "+namePersoTh);
    	int idPerson = -1;
    	thingGroup.setName(namePersoTh);
    	thingGroup.setIdPerson(idPerson);
    	
	    Long idGroup = group.getId();
	    
    	try {

        	Long idThing = thingsDao.insertIntoGroupThings(namePersoTh, idPerson, idGroup);
        	thingGroup.setId(idThing);
         } catch ( DAOException e ) {
             errors.put("getThings", "Échec de la selection de personal thing : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
             e.printStackTrace();
             return null;
         }
    	
    	things.addGroupThing(thingGroup);
    	
    	return things;
    }
    
    
    public Map<String, String> getErrors() {
        return errors;
    }
}
