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

public class ExpensesDao {
    private DAOFactory daoFactory;
	
	public ExpensesDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	
	private static final String SQL_SELECT_ROWEXP = "SELECT * FROM RowExpenses, Persons WHERE id_group=? AND "
			+ "id_person=id_buyer";
	private static final String SQL_SELECT_BENEF = "SELECT * FROM Beneficiaries WHERE id_rowExpenses=?";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public void getExpenses(Group group, Expenses expenses) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatementSelectExp = null;
		PreparedStatement preparedStatementSelectBenef = null;
		ResultSet resultSetRowExp = null;
		ResultSet resultSetRowBenef = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = (Connection) daoFactory.getConnection();

			preparedStatementSelectExp = initialisationRequetePreparee(
					connexion, SQL_SELECT_ROWEXP, false, group.getId());
			resultSetRowExp = preparedStatementSelectExp.executeQuery();
			
			ArrayList<RowExpenses> listRowExpenses = new ArrayList<RowExpenses>();

			while (resultSetRowExp.next()) {
				RowExpenses rowE = new RowExpenses();
				// on recup l'id de la row
				Long idRow = resultSetRowExp.getLong("id_row");
				// on recup le buyer
				String nameBuyer = resultSetRowExp.getString("name_person");
				int idBuyer = resultSetRowExp.getInt("id_person");
				Person buyer = new Person();
				buyer.setId((long) idBuyer);
				buyer.setName(nameBuyer);

				int amount = resultSetRowExp.getInt("amount");
				String description = resultSetRowExp.getString("description");

				rowE.setId(idRow);
				rowE.setBuyer(buyer);
				rowE.setAmount(amount);
				rowE.setDescription(description);

				// on trouve la liste des beneficiaries pour cette ligne
				preparedStatementSelectBenef = initialisationRequetePreparee(
						connexion, SQL_SELECT_BENEF, false, idRow);
				resultSetRowBenef = preparedStatementSelectBenef.executeQuery();
				HashMap<Long, String> mapCheckBox = new HashMap<Long, String>();
				while (resultSetRowBenef.next()) {
					Long idBenef = resultSetRowBenef.getLong("id_benef");
					mapCheckBox.put(idBenef, "checked");
				}

				rowE.setMapCheckBox(mapCheckBox);
				listRowExpenses.add(rowE);
			}
			expenses.setListRowExpenses(listRowExpenses);

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSetRowBenef,
					preparedStatementSelectBenef, connexion);
			fermeturesSilencieuses(resultSetRowExp, preparedStatementSelectExp,
					connexion);
		}
	}
	
	
	
	private static final String SQL_INSERT_NEW_ROW = "INSERT INTO RowExpenses (id_buyer, amount, description, id_group)"
			+ "VALUES (?, ?, ?, ?)";
	
	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public int createNewRow(int idBuyer, int amount, String descript, long idGroup) throws DAOException {

		Connection connexion = null;
	    PreparedStatement preparedStatementInsert = null;
	    ResultSet valeursAutoGenerees = null;
	    
	    

		try {

	        connexion = (Connection) daoFactory.getConnection();
			// si le nom n'est pas pris, on insert le groupe dans la base
        	preparedStatementInsert = initialisationRequetePreparee( connexion, SQL_INSERT_NEW_ROW, true, idBuyer, amount, descript, idGroup);
	        int statut = preparedStatementInsert.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatementInsert.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	        	return (int) valeursAutoGenerees.getLong( 1 );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
		
		
	     
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( valeursAutoGenerees, preparedStatementInsert, connexion );
		    }
	}
	
	private static final String SQL_UPDATE_ROW = "UPDATE RowExpenses SET id_buyer=?, amount=?, description=? WHERE id_row=?";
	
	public void updateRow(int idRow, int idBuyer, int amount, String descript){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ROW, false, idBuyer, amount, descript, idRow);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
	
	private static final String SQL_DELETE_BENEF = "DELETE FROM Beneficiaries WHERE id_rowExpenses = ?";
	
	public void deleteBeneficiaries(int idRow){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_BENEF, false, idRow);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}


	private static final String SQL_INSERT_BENEFICIARIES = "INSERT INTO Beneficiaries (id_rowExpenses, id_benef) VALUES (?, ?)";
	
	public void insertIntoBeneficiaries(int idRow, int idBenef){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_BENEFICIARIES, false, idRow, idBenef);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
		
	}
}