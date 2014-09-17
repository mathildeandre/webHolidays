package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Group;
import beans.Person;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PersonDao {
	

    private DAOFactory daoFactory;
    
	public PersonDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	

	private static final String SQL_INSERT = "INSERT INTO Persons (name_person, pwd_person, mail_person, id_group, is_admin) VALUES (?, ?, ?, ?, ?)";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	public long create( Person person ) throws DAOException {
		System.out.println("creation user !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, person.getName(), person.getPwd(), person.getEmail(), person.getIdGroup(), person.isAdmin());
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
}