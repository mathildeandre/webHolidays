package beans;

import java.sql.Timestamp;

public class Group {

    private Long      id;
    private String    name;
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
	public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
}