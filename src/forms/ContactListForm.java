package forms;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Group;
import beans.Person;
import dao.ContactListDao;
import dao.DAOException;
import forms.ContactListForm;


public class ContactListForm extends Exception {

	public static final String CONF_DAO_FACTORY = "daofactory";
	private Map<String, String> errors;
    private ContactListDao     contactListDao;


	public ContactListForm(ContactListDao contactListDao) throws ServletException {
		this.contactListDao = contactListDao;
		errors = new HashMap<String, String>();
       }

	
	public void addNewMemberInContactLists(Group group, Person personAdded){
		for(int i=0; i<group.getNbPerson(); i++){
			Person persGroup = group.getListMembers().get(i);
			if(persGroup.getId() != personAdded.getId()){
				//on test mnt si le couple exist deja dans listContact
				boolean isInContactList = contactListDao.isCoupleInContactList(persGroup.getId(), personAdded.getId());
				//si non on le cree!
				if(!isInContactList){
					contactListDao.insertContactList(persGroup.getId(), personAdded.getId());
				}
				
			}
		}
	}

	public ArrayList<Person> getContactList(Person person){
		ArrayList<Person> listContact = new ArrayList<Person>();
		try {
			listContact = contactListDao.getContactList(person);
			
        } catch ( DAOException e ) {
        	 errors.put("getContactList", "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
            e.printStackTrace();
            return null;
        }
		
		return listContact;
	}
	public void addExistingPerson(HttpServletRequest request, ArrayList<Person> contactList){
		String loginAdd = request.getParameter("PAsearchPerson");
		
		HttpSession session = request.getSession(); 
		//Person connectee
		Person person = (Person) session.getAttribute("person");
		Long idPerson = person.getId();   
		System.out.println("On veut ajouter : "+ loginAdd+" - ns some id : "+idPerson);
		
		Long idUser = null;
		Person personAddList = new Person();
		boolean userExist = contactListDao.findUser(loginAdd, personAddList);//return true si le user exist
		
		
		if(userExist){
			//on verifie qu'il n'est pas already dans la liste de contact 
			boolean alreadyInList = false;
			for(int k=0; k<contactList.size(); k++){
				if(contactList.get(k).getId() == personAddList.getId()){
					alreadyInList = true;
					System.out.println("IS ALREADY IN THE LIST !!!");
				}
			}
			if(!alreadyInList){
				contactListDao.insertContactList(idPerson, personAddList.getId());
				contactList.add(personAddList);
			}
			else{
				errors.put("addExistingPerson", "the person is already in the list!");
			}
		}
		else{
			errors.put("addExistingPerson", "the login does not exist");
		}		
	}

	public void deletePersonList(HttpServletRequest request, ArrayList<Person> contactList){

		String contactDelete = request.getParameter("listContactsDelete");
		String[] tabContactDel = contactDelete.split("-");
		int idPersonDelete = Integer.parseInt(tabContactDel[0]);
		int placeInList = Integer.parseInt(tabContactDel[1]);
		
		
		HttpSession session = request.getSession(); 
		Person person = (Person) session.getAttribute("person");
		Long idPerson = person.getId();   
		System.out.println("On veut delete id : "+ idPersonDelete+" - ns some id : "+idPerson);
		System.out.println("place dans liste : "+ placeInList+" - REAL dans list , nom pers a cette place: "+contactList.get(placeInList).getLogin());

		contactListDao.deleteContactList(idPerson, idPersonDelete);
		contactList.remove(placeInList);
	}
	
	
	public Map<String, String> getErrors() {
		return errors;
	}
}
