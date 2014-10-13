package beans;

import java.util.HashMap;

public class ColDoodle {

	private Long id;
    String name;  
	private HashMap<Long,String> mapCheckBox; //on ecris "checked" ou non
	boolean isCheckBox;
    
    public boolean isCheckBox() {
		return isCheckBox;
	}


	public void setCheckBox(boolean isCheckBox) {
		this.isCheckBox = isCheckBox;
	}


	public ColDoodle(){
    	mapCheckBox = new HashMap<Long,String>();
    }
    

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public HashMap<Long, String> getMapCheckBox() {
		return mapCheckBox;
	}


	public void setMapCheckBox(HashMap<Long, String> mapCheckBox) {
		this.mapCheckBox = mapCheckBox;
	}
	
    
}