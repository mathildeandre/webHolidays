package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static dao.DAOUtilitaire.*;
import beans.ColDoodle;
import beans.Doodle;
import beans.Group;

public class DoodleDao {
	private DAOFactory daoFactory;

	public DoodleDao( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}



	private static final String SQL_INSERT_DOODLE = "INSERT INTO Doodle (name_doodle, id_group) VALUES (?, ?)";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long createDoodle(Group group, String name) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatementDoodle = null;	
		ResultSet valeursAutoGenerees = null;


		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatementDoodle = initialisationRequetePreparee( connexion, SQL_INSERT_DOODLE, true, name, group.getId());
			int statut = preparedStatementDoodle.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création du doodle, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatementDoodle.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				return valeursAutoGenerees.getLong( 1 );
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
			} else {
				throw new DAOException( "Échec de la création du doodle en base, aucun ID auto-généré retourné." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatementDoodle, connexion );
		}
	}

	private static final String SQL_SELECT_DOODLE = "SELECT * FROM Doodle WHERE id_group=?";
	private static final String SQL_SELECT_COL = "SELECT * FROM ColumnDoodle WHERE id_doodle=?";
	private static final String SQL_SELECT_CHECKED = "SELECT * FROM PersonChecked WHERE id_columnDoodle=?";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public void getDoodles(Group group, ArrayList<Doodle> listDoodles) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatementSelectDood = null;
		PreparedStatement preparedStatementSelectCol = null;
		PreparedStatement preparedStatementSelectCheck = null;
		ResultSet resultSetDood = null;
		ResultSet resultSetCol = null;
		ResultSet resultSetCheck = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();

			preparedStatementSelectDood = initialisationRequetePreparee(
					connexion, SQL_SELECT_DOODLE, false, group.getId());
			resultSetDood = preparedStatementSelectDood.executeQuery();

			// recuperation des doodle
			while (resultSetDood.next()) {
				Doodle doodle = new Doodle();
				long idDood = resultSetDood.getLong("id_doodle");
				String nameDood = resultSetDood.getString("name_doodle");

				ArrayList<ColDoodle> listColDoodle = new ArrayList<ColDoodle>();

				doodle.setIdDoodle(idDood);
				doodle.setNameDoodle(nameDood);

				preparedStatementSelectCol = initialisationRequetePreparee(
						connexion, SQL_SELECT_COL, false, idDood);
				resultSetCol = preparedStatementSelectCol.executeQuery();
				// recuperation des colonnes
				while (resultSetCol.next()) {
					ColDoodle colDood = new ColDoodle();
					long idCol = resultSetCol.getLong("id_column");
					String nameCol = resultSetCol.getString("name_column");
					boolean isChekBox = resultSetCol.getBoolean("is_checkBox");
					colDood.setCheckBox(isChekBox);
					colDood.setId(idCol);
					colDood.setName(nameCol);

					preparedStatementSelectCheck = initialisationRequetePreparee(
							connexion, SQL_SELECT_CHECKED, false, idCol);
					resultSetCheck = preparedStatementSelectCheck.executeQuery();
					HashMap<Long,String> mapCheckBox = new HashMap<Long,String>();

					while (resultSetCheck.next()) {
						long idPerson = resultSetCheck.getLong("id_person");
						mapCheckBox.put(idPerson, "checked");
					}
					colDood.setMapCheckBox(mapCheckBox);
					listColDoodle.add(colDood);
					doodle.setListColDoodle(listColDoodle);
				}
				listDoodles.add(doodle);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSetDood,preparedStatementSelectDood, connexion);
			fermeturesSilencieuses(resultSetCol, preparedStatementSelectCol, connexion);
			fermeturesSilencieuses(resultSetCheck, preparedStatementSelectCheck, connexion);
		}
	}


	private static final String SQL_INSERT_NEW_COL = "INSERT INTO ColumnDoodle (name_column, id_doodle, is_checkBox)"
			+ "VALUES (?, ?, ?)";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public int createNewCol(String nameCol, int idDoodle) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatementInsert = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = (Connection) daoFactory.getConnection();
			// si le nom n'est pas pris, on insert le groupe dans la base
			preparedStatementInsert = initialisationRequetePreparee( connexion, SQL_INSERT_NEW_COL, true, nameCol, idDoodle, true);
			int statut = preparedStatementInsert.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de col, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatementInsert.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				return (int) valeursAutoGenerees.getLong( 1 );
			} else {
				throw new DAOException( "Échec de la création de col en base, aucun ID auto-généré retourné." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatementInsert, connexion );
		}
	}

	private static final String SQL_UPDATE_COL = "UPDATE ColumnDoodle SET name_column=?, id_doodle=?, is_checkBox=? WHERE id_column=?";

	public void updateCol(String nameCol, int idDoodle, int idCol){
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_COL, false, nameCol, idDoodle, true, idCol);
			preparedStatement.executeUpdate();

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}
	
private static final String SQL_DELETE_PERSONCHECKED = "DELETE FROM PersonChecked WHERE id_columnDoodle = ?";
	
	public void deletePersonChecked(int idCol){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PERSONCHECKED, false, idCol);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
	
	
private static final String SQL_INSERT_PERSONCHECKED = "INSERT INTO PersonChecked (id_columnDoodle, id_person) VALUES (?, ?)";
	
	public void insertIntoPersonChecked(int idCol, int idPers){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_PERSONCHECKED, false, idCol, idPers);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
		
	}

}