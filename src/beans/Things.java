package beans;

import java.util.ArrayList;

public class Things {


	private ArrayList<ThingPersonal> listThingPersonal;
	private int sizePerso;
	private ArrayList<ThingGroup> listThingGroup;
	private int sizeGroup;
	

	public Things(){
		listThingPersonal = new ArrayList<ThingPersonal>();
		listThingGroup = new ArrayList<ThingGroup>();
		sizePerso = 0;
		sizeGroup = 0;
	}
	

    public void addPersonalThing(ThingPersonal thingPersonal){
    	listThingPersonal.add(thingPersonal);
    	sizePerso ++;
    }
    public void removePersonalThing(int index){
    	listThingPersonal.remove(index);
    	sizePerso --;
    }
	public ArrayList<ThingPersonal> getListThingPersonal() {
		return listThingPersonal;
	}
	public void setListThingPersonal(ArrayList<ThingPersonal> listThingPersonal) {
		this.listThingPersonal = listThingPersonal;
		sizePerso = listThingPersonal.size();
	}
    

    public void addGroupThing(ThingGroup thingGroup){
    	listThingGroup.add(thingGroup);
    	sizeGroup ++;
    }
    public void removeGroupThing(int index){
    	listThingGroup.remove(index);
    	sizeGroup --;
    }
	public ArrayList<ThingGroup> getListThingGroup() {
		return listThingGroup;
	}
	public void setListThingGroup(ArrayList<ThingGroup> listThingGroup) {
		this.listThingGroup = listThingGroup;
		sizeGroup = listThingGroup.size();
	}

	public int getSizePerso() {
		return sizePerso;
	}
	public int getSizeGroup() {
		return sizeGroup;
	}
    
    

    
}