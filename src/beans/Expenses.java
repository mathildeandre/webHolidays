package beans;

import java.util.ArrayList;

public class Expenses {

	
	private ArrayList<RowExpenses> listRowExpenses;
	private int size;
	
	public Expenses(){
		listRowExpenses = new ArrayList<RowExpenses>();
		
	}

	public ArrayList<RowExpenses> getListRowExpenses() {
		return listRowExpenses;
	}

	public void setListRowExpenses(ArrayList<RowExpenses> listRowExpenses) {
		this.listRowExpenses = listRowExpenses;
		size = listRowExpenses.size();
	}
	
	public int getSize(){
		return size;
	}
    

    
}