package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static dao.DAOUtilitaire.*;
import beans.Group;

public class GroupDaoImpl implements GroupDao {
    private DAOFactory daoFactory;
	
	GroupDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	
	private static final String SQL_INSERT = "INSERT INTO Groups (name, email, pwd_admin, pwd_members, date_inscription) VALUES (?, ?, ?, ?, NOW())";

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public void creer( Group group ) throws DAOException {
		System.out.println("creation user !!!!!!!!!!!!!!!!!!!");
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = (Connection) daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, group.getName(), group.getEmail(), group.getPwdAdmin(), group.getPwdMembers() );
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	            group.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
	}
	
//	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ?";
//	
//	@Override
//	public Group trouver(String email) throws DAOException {
//		// TODO Auto-generated method stub
//		Connection connexion = null;
//	    PreparedStatement preparedStatement = null;
//	    ResultSet resultSet = null;
//	    Group utilisateur = null;
//
//	    try {
//	        /* Récupération d'une connexion depuis la Factory */
//	        connexion = (Connection) daoFactory.getConnection();
//	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
//	        resultSet = preparedStatement.executeQuery();
//	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
//	        if ( resultSet.next() ) {
//	            utilisateur = DAOUtilitaire.map( resultSet );
//	        }
//	    } catch ( SQLException e ) {
//	        throw new DAOException( e );
//	    } finally {
//	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
//	    }
//
//	    return utilisateur;
//	}

}