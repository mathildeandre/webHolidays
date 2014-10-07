package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Group;
import beans.Person;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PersonDao {
	

    private DAOFactory daoFactory;
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    
	public PersonDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	

	private static final String SQL_INSERT = "INSERT INTO Persons (name_person, login_person, pwd_person, mail_person, is_new) VALUES (?, ?, ?, ?, ?)";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long create( Person person ) throws DAOException {
		System.out.println("creation user !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, person.getName(), person.getLogin(), person.getPwd(), person.getEmail(), person.isNew());
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
	            //person.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
	}
	
	private static final String SQL_SELECT_LOGIN = "SELECT id_person FROM Persons WHERE login_person=?";

	public boolean checkLogin( String login ) throws DAOException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSetLogin = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_LOGIN, false, login);
		        resultSetLogin = preparedStatement.executeQuery();
		        /* Analyse du statut retourné par la requête d'insertion */
		        if ( resultSetLogin.next() ) {
		            return false;
		        } else {
		        	return true;	
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( resultSetLogin, preparedStatement, connexion );
		    }
	}
	
	private static final String SQL_SELECT_PERSON = "SELECT * FROM Persons WHERE login_person=?";

	public boolean checkUser(Person person, String login, String pwd) throws DAOException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSetPerson = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PERSON, false, login);
		        resultSetPerson = preparedStatement.executeQuery();

		        if ( resultSetPerson.next() ) {
		        	String pwdPerson = resultSetPerson.getString("pwd_person");
		        	
		        	ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		            passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
		            passwordEncryptor.setPlainDigest( false );
		            Boolean boolPwd = passwordEncryptor.checkPassword(pwd, pwdPerson);
		            if(boolPwd){
		            	// Creation de la person
		            	String nameP = resultSetPerson.getString("name_person");
		            	String emailP = resultSetPerson.getString("mail_person");
		            	boolean isNew = resultSetPerson.getBoolean("is_new");
		            	person.setName(nameP);
		            	person.setLogin(login);
		            	person.setPwd(pwd);
		            	person.setNew(isNew);
		            	// le pwd correspond bien à celui du login
		                System.out.println("Personne crée !");
		            	return true;
		            }else{
		            	// mauvais pwd
		                System.out.println("Mauvais pwd !");
		            	return false;
		            }
		        }else{
		        	// login non existant
	                System.out.println("Login non existant!");
		        	return false;
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( resultSetPerson, preparedStatement, connexion );
		    }
	}
	
}