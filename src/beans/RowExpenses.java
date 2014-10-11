package beans;

import java.util.HashMap;

public class RowExpenses {

	
    private Person buyer;
    private int amount;   
	private HashMap<Long,String> mapCheckBox; //on ecris "checked" ou non
    private String    description;
    
    public RowExpenses(){
    	mapCheckBox = new HashMap<Long,String>();
    }
    
	public Person getBuyer() {
		return buyer;
	}
	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public HashMap<Long, String> getMapCheckBox() {
		return mapCheckBox;
	}
	public void setMapCheckBox(HashMap<Long, String> mapCheckBox) {
		this.mapCheckBox = mapCheckBox;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}