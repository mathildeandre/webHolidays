package beans;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Group {

    private Long      id;
    private String    name;
    private Timestamp dateInscription;
    private ArrayList<Person> listMembers;
    private int nbPerson;
    private int idAdmin;

    public Long getId() {
        return id;
    }
    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
    
    public ArrayList<Person> getListMembers(){
    	return listMembers;
    }
    
    public void addPersonIntoListMembers(Person person){
    	listMembers.add(person);
    	nbPerson ++;
    }
    
    public boolean containsMember(String login){
    	for(int i=0; i<listMembers.size(); i++){
    		if(listMembers.get(i).getLogin().equalsIgnoreCase(login)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public void setListMembers(ArrayList<Person> aListMembers){
    	listMembers = new ArrayList<Person>();
    	listMembers.clear();
    	for(int i=0; i<aListMembers.size(); i++){
    		listMembers.add(aListMembers.get(i));
    	}
    	nbPerson = listMembers.size();
    }
    // TODO function deleteMembers
	public int getNbPerson() {
		return nbPerson;
	}
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
}