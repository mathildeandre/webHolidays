package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Group;
import dao.GroupDao;

public class ConnexionForm {


    private GroupDao groupDao;
    
    
	public ConnexionForm( GroupDao groupDao ) {
        this.groupDao = groupDao;
    }
	
	
	public Group connectUser(HttpServletRequest request){
		return null;
	}
}
