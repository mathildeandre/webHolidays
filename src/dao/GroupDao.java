package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import static dao.DAOUtilitaire.*;
import beans.Group;

public class GroupDao {
    private DAOFactory daoFactory;
    private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	public GroupDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	
	private static final String SQL_INSERT = "INSERT INTO Groups (name_group, date_inscription) VALUES (?, NOW())";
	private static final String SQL_SELECT_NAME = "SELECT id_group FROM Groups WHERE name_group=?";


	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long createGroup( Group group ) throws DAOException {
		System.out.println("creation group !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatementInsert = null;
	    PreparedStatement preparedStatementSelect = null;
	    ResultSet valeursAutoGenerees = null;
	    ResultSet resultSetName = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        
	        
	        preparedStatementSelect = initialisationRequetePreparee( connexion, SQL_SELECT_NAME, false, group.getName());
	        resultSetName = preparedStatementSelect.executeQuery();

	        if ( resultSetName.next() ) {
	        	// le nom existe déjà ! 
	        	System.out.println("The name of the group already exists ! ");
	        	return 0;
	        }else{
	        	// si le nom n'est pas pris, on insert le groupe dans la base
	        	preparedStatementInsert = initialisationRequetePreparee( connexion, SQL_INSERT, true, group.getName());
		        int statut = preparedStatementInsert.executeUpdate();
		        /* Analyse du statut retourné par la requête d'insertion */
		        if ( statut == 0 ) {
		            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
		        }
		        /* Récupération de l'id auto-généré par la requête d'insertion */
		        valeursAutoGenerees = preparedStatementInsert.getGeneratedKeys();
		        if ( valeursAutoGenerees.next() ) {
		        	return valeursAutoGenerees.getLong( 1 );
		        } else {
		            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
		        }
	        }
	        
	        
	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatementInsert, connexion );
	        fermeturesSilencieuses( resultSetName, preparedStatementSelect, connexion );
	    }
	}
	
	private static final String SQL_INSERT_BELONG = "INSERT INTO BelongTo (id_person, id_group) VALUES (?,?)";

	public boolean registerGroup(long idPers, long idGroup) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        
		   // PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement( SQL_SELECT_GROUP, Statement.NO_GENERATED_KEYS );
		   // preparedStatement.setString(1, nameGroup);
	     
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_INSERT_BELONG, false, idPers, idGroup );
	        int statut = preparedStatementGroup.executeUpdate();

	        if ( statut == 0) {
	            throw new DAOException( "Échec de l'association groupe person, aucune ligne ajoutée dans la table." ); 
	        }
	        else{
	        	return true;
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetGroup, preparedStatementGroup, connexion );
	    }
	}
	
	private static final String SQL_SELECT_GROUP = "SELECT * FROM Groups WHERE name_group=?";

	public boolean checkGroup(String nameGroup) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        
		   // PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement( SQL_SELECT_GROUP, Statement.NO_GENERATED_KEYS );
		   // preparedStatement.setString(1, nameGroup);
	     
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_SELECT_GROUP, false, nameGroup);
	        resultSetGroup = preparedStatementGroup.executeQuery();

	        if ( resultSetGroup.next()) {
	        	return false;
	        }
	        else{
	        	return true;
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetGroup, preparedStatementGroup, connexion );
	    }
	}
	
	
	
}