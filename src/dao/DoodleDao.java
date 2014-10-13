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
import beans.ColDoodle;
import beans.Doodle;
import beans.Expenses;
import beans.Group;
import beans.Person;
import beans.RowExpenses;

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

}