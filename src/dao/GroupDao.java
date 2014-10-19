package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static dao.DAOUtilitaire.*;
import beans.Group;
import beans.Person;

public class GroupDao {
    private DAOFactory daoFactory;
    private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	public GroupDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	
	private static final String SQL_INSERT = "INSERT INTO Groups (name_group, date_inscription, login_admin) VALUES (?, NOW(), ?)";
	
	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long createGroup( Group group, String loginAmdin ) throws DAOException {
		System.out.println("creation group !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatementInsert = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	       
	        // si le nom n'est pas pris, on insert le groupe dans la base
	        preparedStatementInsert = initialisationRequetePreparee( connexion, SQL_INSERT, true, group.getName(), loginAmdin);
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
	        
	        
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatementInsert, connexion );
	    }
	}
	
	private static final String SQL_INSERT_BELONG = "INSERT INTO BelongTo (id_person, id_group, has_rights) VALUES (?,?, ?)";

	public void registerGroup(long idPers, long idGroup, int hasRights) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        
		   // PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement( SQL_SELECT_GROUP, Statement.NO_GENERATED_KEYS );
		   // preparedStatement.setString(1, nameGroup);
	     
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_INSERT_BELONG, false, idPers, idGroup, hasRights );
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
	

	private static final String SQL_SELECT_MEMBERS = 
			"SELECT Persons.id_person, name_person, login_person, pwd_person, mail_person, is_new "
			+ "FROM BelongTo,Persons "
			+ "WHERE BelongTo.id_group = ? "
			+ "AND BelongTo.id_person = Persons.id_person";
	
	public ArrayList<Person> getMembers(Group group){
		Connection connexion = null;
	    PreparedStatement preparedStatementGroup = null;
	    ResultSet resultSetGroup = null;

	    ArrayList<Person> listMembers = new ArrayList<Person>();
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatementGroup = initialisationRequetePreparee( connexion, SQL_SELECT_MEMBERS, false, group.getId());
	        resultSetGroup = preparedStatementGroup.executeQuery();

	        while(resultSetGroup.next()){

		        Person person = new Person();
	        	person.setId(resultSetGroup.getLong("id_person"));
	        	person.setName(resultSetGroup.getString("name_person"));
	        	person.setLogin(resultSetGroup.getString("login_person"));
	        	
	        	person.setPwd(resultSetGroup.getString("pwd_person"));
	        	person.setEmail(resultSetGroup.getString("mail_person"));
	        	person.setNew(resultSetGroup.getBoolean("is_new"));
	        	listMembers.add(person);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetGroup, preparedStatementGroup, connexion );
	    }
		
		return listMembers;
	}
	
private static final String SQL_UPDATE_RIGHTS = "UPDATE BelongTo SET has_rights=? WHERE id_person=? AND id_group=?";
	
	public void updateRights(int right, Long idGroup, int idPersonNewRights){
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    
		try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = (Connection) daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_RIGHTS, false, right, idPersonNewRights, idGroup);
		        preparedStatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses(preparedStatement, connexion );
		    }
	}
	
	
	private static final String SQL_SELECT_RIGHTS = "SELECT has_rights FROM BelongTo WHERE id_person = ? AND id_group = ?";

	public int hasRight(Long idGroup, Long idPerson) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatementBelongTo = null;
	    ResultSet resultSetBelongTo = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	     
	        preparedStatementBelongTo = initialisationRequetePreparee( connexion, SQL_SELECT_GROUP, false, idPerson, idGroup);
	        resultSetBelongTo = preparedStatementBelongTo.executeQuery();

	        if ( resultSetBelongTo.next()) {
	        	return resultSetBelongTo.getInt("has_rights");
	        }
	        else{
	        	return 0;
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSetBelongTo, preparedStatementBelongTo, connexion );
	    }
	}
}