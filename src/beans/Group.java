package beans;

import java.sql.Timestamp;

public class Group {

    private Long      id;
    private String    name;
    private String    email;
    private String    pwdAdmin;
    private String    pwdMembers;
    private Timestamp dateInscription;

    public Long getId() {
        return id;
    }
    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwdAdmin() {
		return pwdAdmin;
	}
	public void setPwdAdmin(String pwdAdmin) {
		this.pwdAdmin = pwdAdmin;
	}
	public String getPwdMembers() {
		return pwdMembers;
	}
	public void setPwdMembers(String pwdMembers) {
		this.pwdMembers = pwdMembers;
	}
	public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
}