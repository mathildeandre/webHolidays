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
    private static final String ALGO_CHIFFREMENT = "SHA-256";
	
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
				int idRow = resultSetRowExp.getInt("id_row");
				// on recup le buyer
				String nameBuyer = resultSetRowExp.getString("name_person");
				int idBuyer = resultSetRowExp.getInt("id_person");
				Person buyer = new Person();
				buyer.setId((long) idBuyer);
				buyer.setName(nameBuyer);

				int amount = resultSetRowExp.getInt("amount");
				String description = resultSetRowExp.getString("description");

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

}