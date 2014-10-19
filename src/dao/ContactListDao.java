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

public class ContactListDao {
	private DAOFactory daoFactory;

	public ContactListDao( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}


	/*On recupere ici tout les contact de la list d'une person */
	 // possible : Select * from ContactList where id_person1 = 2 OR id_person2 = 2;
	private static final String SQL_SELECT_CONTACT_LIST = 
			"(SELECT id_person, login_person, name_person, mail_person "
			+ "FROM ContactList, Persons  "
			+ "WHERE id_person1 = ? AND id_person2 = Persons.id_person) "
		+ "UNION "
			+ "(SELECT id_person, login_person, name_person, mail_person "
			+ "FROM ContactList, Persons  "
			+ "WHERE id_person2 = ? AND id_person1 = Persons.id_person)";

	public ArrayList<Person> getContactList(Person person) throws DAOException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSetPersons = null;
	    
	    ArrayList<Person> listContact = new ArrayList<Person>();
	    
		try {
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_CONTACT_LIST, false, person.getId(), person.getId());
		        resultSetPersons = preparedStatement.executeQuery();

		        while ( resultSetPersons.next() ) {
		        	Long idPerson = resultSetPersons.getLong("id_person");
		        	String loginPerson = resultSetPersons.getString("login_person");
		        	String namePerson = resultSetPersons.getString("name_person");
		        	String emailPerson = resultSetPersons.getString("mail_person");
		        	
		        	Person personContact = new Person();
		        	personContact.setId(idPerson);
		        	personContact.setLogin(loginPerson);
		        	personContact.setName(namePerson);
		        	personContact.setEmail(emailPerson);
		        	
		        	listContact.add(personContact);
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( resultSetPersons, preparedStatement, connexion );
		    }

        return listContact;
	}
	
	
	
	private static final String SQL_FIND_PERSON = "SELECT * FROM Persons WHERE login_person=?";

	public boolean findUser( String login, Person person) throws DAOException {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSetLogin = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_FIND_PERSON, false, login);
		        resultSetLogin = preparedStatement.executeQuery();
		        /* Analyse du statut retourné par la requête d'insertion */
		        if ( resultSetLogin.next() ) {
		        	Long id = resultSetLogin.getLong("id_person");
		        	String name = resultSetLogin.getString("name_person");
		        	String email = resultSetLogin.getString("mail_person");
		        	
		        	person.setId(id);
		        	person.setName(name);
		        	person.setLogin(login);
		        	person.setEmail(email);
		        	
		            return true;
		        } else {
		        	return false;	
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( resultSetLogin, preparedStatement, connexion );
		    }
	}
	
	
	
	private static final String SQL_INSERT_CONTACT_LIST = "INSERT INTO ContactList (id_person1, id_person2) VALUES (?, ?)";

	public void insertContactList(Long idPerson1, Long idPerson2) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_INSERT_CONTACT_LIST, false, idPerson1, idPerson2 );
	        int statut = preparedStatementGroup.executeUpdate();

	        if ( statut == 0) {
	            throw new DAOException( "Échec de l'association groupe person, aucune ligne ajoutée dans la table." ); 
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetGroup, preparedStatementGroup, connexion );
	    }
	}
	
private static final String SQL_DELETE_CONTACT_LIST =
	"delete from ContactList where (id_person1 = ? and id_person2 = ?) or (id_person1 = ? and id_person2 = ?)";
	
	public void deleteContactList(Long idPerson1, int idPerson2){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_CONTACT_LIST, false, idPerson1, idPerson2, idPerson2, idPerson1);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
	
	private static final String SQL_SELECT_IN_CONTACTLIST =
			"select *  from ContactList where (id_person1 = ? and id_person2 = ?) or (id_person1 = ? and id_person2 = ?)";
	public boolean isCoupleInContactList(Long idPerson1, Long idPerson2){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSetLogin = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_IN_CONTACTLIST, false, idPerson1, idPerson2, idPerson2, idPerson1);
		        resultSetLogin = preparedStatement.executeQuery();
		        /* Analyse du statut retourné par la requête d'insertion */
		        if ( resultSetLogin.next() ) {
		        	
		            return true;
		        } else {
		        	return false;	
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( resultSetLogin, preparedStatement, connexion );
		    }
	}

}