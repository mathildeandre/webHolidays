package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static dao.DAOUtilitaire.*;
import beans.Group;
import beans.ThingGroup;
import beans.ThingPersonal;
import beans.Things;

public class ThingsDao {
    private DAOFactory daoFactory;
	
	public ThingsDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
private static final String SQL_DELETE_PERSONAL_THING = "DELETE FROM PersonalThings WHERE id_thing = ?";
	
	public void deletePersonalThing(int idThingToDelete){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PERSONAL_THING, false, idThingToDelete);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
private static final String SQL_DELETE_GROUP_THING = "DELETE FROM GroupThings WHERE id_thing = ?";
	
	public void deleteGroupThing(int idThingToDelete){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_GROUP_THING, false, idThingToDelete);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
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

				Long idPersoThing = resultSetPersoThings.getLong("id_thing");
				String nameThing = resultSetPersoThings.getString("name_thing");
				thPerso.setId(idPersoThing);
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
	
	public Long insertIntoPersonalThings(String namePersoTh, Long idGroup){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_PERSONAL_THING, true, namePersoTh, idGroup);
		        preparedStatement.executeUpdate();
		       
		        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			  	if ( valeursAutoGenerees.next() ) {
			      	return valeursAutoGenerees.getLong( 1 );
			  	} else {
			       	throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			  	}
			  	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion );
		    }
		 
	}
	
	
private static final String SQL_INSERT_GROUP_THING = "INSERT INTO GroupThings (name_thing, id_group, id_person) VALUES (?, ?, ?)";
	
	public Long insertIntoGroupThings(String namePersoTh, int idPerson, Long idGroup){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_GROUP_THING, true, namePersoTh, idGroup, idPerson);
		        preparedStatement.executeUpdate();
		       
		        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			  	if ( valeursAutoGenerees.next() ) {
			      	return valeursAutoGenerees.getLong( 1 );
			  	} else {
			       	throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			  	}
			  	
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
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