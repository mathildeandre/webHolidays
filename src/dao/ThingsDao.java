package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import static dao.DAOUtilitaire.*;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.RowExpenses;
import beans.ThingGroup;
import beans.ThingPersonal;
import beans.Things;

public class ThingsDao {
    private DAOFactory daoFactory;
	
	public ThingsDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	

	private static final String SQL_SELECT_PERSONAL_THINGS = 
			"select * from PersonalThings where id_group = ?";

	private static final String SQL_SELECT_GROUP_THINGS = 
			"select * from GroupThings where id_group = ?";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public Things getThings(Group group) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatementSelectPersoThings = null;
		ResultSet resultSetPersoThings = null;
		
		PreparedStatement preparedStatementSelectGroupThings = null;
		ResultSet resultSetGroupThings = null;


		Things things = new Things();
		
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();

			preparedStatementSelectPersoThings = initialisationRequetePreparee(
					connexion, SQL_SELECT_PERSONAL_THINGS, false, group.getId());
			resultSetPersoThings = preparedStatementSelectPersoThings.executeQuery();
			
			preparedStatementSelectGroupThings = initialisationRequetePreparee(
					connexion, SQL_SELECT_GROUP_THINGS, false, group.getId());
			resultSetGroupThings = preparedStatementSelectGroupThings.executeQuery();
			
			
			ArrayList<ThingPersonal> listThingPersonal = new ArrayList<ThingPersonal>();
			while (resultSetPersoThings.next()) {
				ThingPersonal thPerso = new ThingPersonal();
				
				String nameThing = resultSetPersoThings.getString("name_thing");
				thPerso.setName(nameThing);
				listThingPersonal.add(thPerso);
			}
			things.setListThingPersonal(listThingPersonal);

			ArrayList<ThingGroup> listThingGroup = new ArrayList<ThingGroup>();
			while (resultSetGroupThings.next()) {
				ThingGroup thGroup = new ThingGroup();
				
				String nameThing = resultSetGroupThings.getString("name_thing");
				int idPersonThing = resultSetGroupThings.getInt("id_person");
				Long idThing = resultSetGroupThings.getLong("id_thing");
				thGroup.setName(nameThing);
				thGroup.setIdPerson(idPersonThing);
				thGroup.setId(idThing);
				listThingGroup.add(thGroup);
			}
			things.setListThingGroup(listThingGroup);
			
			

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSetPersoThings,
					preparedStatementSelectPersoThings, connexion);
			fermeturesSilencieuses(resultSetGroupThings, 
					preparedStatementSelectGroupThings, connexion);
		}
		
		return things;
	}
	
	
	
	
	
	
	
private static final String SQL_INSERT_PERSONAL_THING = "INSERT INTO PersonalThings (name_thing, id_group) VALUES (?, ?)";
	
	public void insertIntoPersonalThings(String namePersoTh, Long idGroup){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_PERSONAL_THING, false, namePersoTh, idGroup);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
		 
	}
	
	
private static final String SQL_INSERT_GROUP_THING = "INSERT INTO GroupThings (name_thing, id_group, id_person) VALUES (?, ?, ?)";
	
	public void insertIntoGroupThings(String namePersoTh, int idPerson, Long idGroup){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_GROUP_THING, false, namePersoTh, idGroup, idPerson);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
		
	}
	
	
	
private static final String SQL_UPDATE_GROUP_THING = "UPDATE GroupThings SET id_person=? WHERE id_thing=?";
	public void updateGroupThings(int idThingGroup, int idThingPerson){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_GROUP_THING, false, idThingPerson, idThingGroup);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
	
}