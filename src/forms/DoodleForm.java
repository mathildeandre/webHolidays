package forms;

import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ColDoodle;
import beans.Doodle;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.RowExpenses;
import dao.DAOException;
import dao.DoodleDao;
import dao.ExpensesDao;
import dao.GroupDao;

public class DoodleForm extends Exception {

	private DoodleDao doodleDao;
	private Map<String, String> errors;


	public DoodleForm(DoodleDao doodleDao) {
		this.doodleDao = doodleDao;
		errors = new HashMap<String, String>();
	}


	public Doodle createDoodle(HttpServletRequest request, Group group){
		String name = request.getParameter("nameNewDoodle");
		Doodle doodle = new Doodle();
		try {
			long idDoodle = doodleDao.createDoodle(group, name); /* on rempli dans le dao lobjet doodle */
			doodle.setIdDoodle(idDoodle);
			doodle.setNameDoodle(name);
		} catch ( DAOException e ) {
			errors.put("createDoodle", "Échec de la creation du doodle : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
			e.printStackTrace();
			return null;
		}
		return doodle;
	}

	public ArrayList<Doodle> getDoodles(HttpServletRequest request, Group group){
		ArrayList<Doodle> listDoodles = new ArrayList<Doodle>();
		try {
			doodleDao.getDoodles(group, listDoodles); /* on rempli dans le dao lobjet expenses */
		} catch ( DAOException e ) {
			errors.put("getEDodles", "Échec de la selection des doodles : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
			e.printStackTrace();
			return null;
		}
		return listDoodles;
	}


	public Doodle saveDoodle(HttpServletRequest request, Group group){
		Doodle doodle = new Doodle();
		ArrayList<ColDoodle> listColDoodle = new ArrayList<ColDoodle>();

		int idDoodle = Integer.parseInt(request.getParameter("idDoodleHidden"));

		String nameDoodle = request.getParameter("nameDoodleHidden");
		System.out.println("idDoodle : "+idDoodle);
		int nbCol = Integer.parseInt(request.getParameter("nbColumn"));
		int nbPersons = Integer.parseInt(request.getParameter("nbMembers"));
		Long idGroup = group.getId();
		try {

			//System.out.println("NB LIGNEEEES : "+nbLines);
			//traitement de chaque ligne
			for(int i=0; i<nbCol; i++){
				ColDoodle colD = new ColDoodle();
				String idColStr = request.getParameter((i+1)+"idCol");
				System.out.println("ID COL STR !! :" + (i+1)+"idCol" + ",,,,"+idColStr);
				String nameCol = request.getParameter((i+1)+"title");
				System.out.println("nameCol : "+nameCol);
				int idCol;
				if(idColStr == null){ // la col n'existait pas
					idCol = doodleDao.createNewCol(nameCol, idDoodle);
				}else{
					idCol = Integer.parseInt(idColStr);
					doodleDao.updateCol(nameCol, idDoodle, idCol);
					doodleDao.deletePersonChecked(idCol);
				}

				colD.setId((long) idCol);
				colD.setName(nameCol);
				HashMap<Long, String> mapCheckBox = new HashMap<Long, String>();
				for(int j=0; j<nbPersons; j++){
					int idPerson = Integer.parseInt(request.getParameter((j+1)+"idP"));
					String isChecked = request.getParameter(""+(i+1)+(j+1));
					if (isChecked == null){
						System.out.println("aie checkbox null");
					}
					else if (isChecked.equalsIgnoreCase("on")){
						System.out.println("checked !");
						mapCheckBox.put((long) idPerson, "checked");
						System.out.println("idCol : "+idCol+", idPers= "+idPerson);
						doodleDao.insertIntoPersonChecked(idCol, idPerson);
					}
				}


				colD.setMapCheckBox(mapCheckBox);
				listColDoodle.add(colD);
			}
			doodle.setListColDoodle(listColDoodle);
			doodle.setIdDoodle((long) idDoodle);
			doodle.setNameDoodle(nameDoodle);

		} catch ( DAOException e ) {
			errors.put("getExpenses", "Échec de la sauvegarde du TAB : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
			e.printStackTrace();
		}

		return doodle;
	}




	public Map<String, String> getErrors() {
		return errors;
	}
}
