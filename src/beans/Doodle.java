package beans;

import java.util.ArrayList;

public class Doodle {

	long idDoodle;
	String nameDoodle;
	private ArrayList<ColDoodle> listColDoodle;
	private int size;
	
	public Doodle(){
		listColDoodle = new ArrayList<ColDoodle>();
		size = 0;
	}

	public long getIdDoodle() {
		return idDoodle;
	}

	public void setIdDoodle(long idDoodle) {
		this.idDoodle = idDoodle;
	}

	public String getNameDoodle() {
		return nameDoodle;
	}

	public void setNameDoodle(String nameDoodle) {
		this.nameDoodle = nameDoodle;
	}

	public ArrayList<ColDoodle> getListColDoodle() {
		return listColDoodle;
	}

	public void setListColDoodle(ArrayList<ColDoodle> listColDoodle) {
		this.listColDoodle = listColDoodle;
		size = listColDoodle.size();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	

    
}