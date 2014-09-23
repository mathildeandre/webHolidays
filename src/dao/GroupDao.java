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

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long create( Group group ) throws DAOException {
		System.out.println("creation user !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, group.getName());
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	        	return valeursAutoGenerees.getLong( 1 );
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	            //group.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
	}
	
	private static final String SQL_SELECT_GROUP = "SELECT idGroup, nameGroup, pwd_admin, pwd_members FROM Groups WHERE nameGroup=?";
	
	public String findGroup(String nameGroup, String pwdGroup) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;
	    String result = "";

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        
		   // PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement( SQL_SELECT_GROUP, Statement.NO_GENERATED_KEYS );
		   // preparedStatement.setString(1, nameGroup);
	     
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_SELECT_GROUP, false, nameGroup );
	        resultSetGroup = preparedStatementGroup.executeQuery();

	        if ( resultSetGroup.next() ) {
	        	String pwdAdmin = resultSetGroup.getString("pwd_admin");
	        	String pwdMember = resultSetGroup.getString("pwd_members");
	        	
	        	ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	            passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
	            passwordEncryptor.setPlainDigest( false );
	            Boolean boolAdmin = passwordEncryptor.checkPassword(pwdGroup, pwdAdmin);
	            Boolean boolMember = passwordEncryptor.checkPassword(pwdGroup, pwdMember);
	            
		        if ( boolAdmin ) {
		        	result="OK_admin";
		        }
		        else if(boolMember){
		        	result="OK_member";
		        }
		        else{
			        result = "ERROR_pwd";
			    }
		        
	        }
	        else{
	        	result = "ERROR_name";
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetGroup, preparedStatementGroup, connexion );
	    }
	    return result;
	}
}