package beans;

public class Person {

    private Long      id;
    private String    name;
	private String    login;
    private String    pwd;
    private String    email;
    private int isNew; //0 ou 1
    private int hasRights; // 0 ou 1, is not in the SQL table
    private String pwdNewbie;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public int getHasRights() {
		return hasRights;
	}
	public void setHasRights(int hasRights) {
		this.hasRights = hasRights;
	}
	public String getPwdNewbie() {
		return pwdNewbie;
	}
	public void setPwdNewbie(String pwdNewbie) {
		this.pwdNewbie = pwdNewbie;
	}
	

    
}