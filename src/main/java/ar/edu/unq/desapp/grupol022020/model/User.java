package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

//@MappedSuperclass
//@Table(name = "user")
public abstract class User{
//	@Column
	private String name;
//	@Column
	private String mail;
//	@Column
	private String password;
//	@Column
	private String nick;
//	@Column
	private String role;
//	@Column
	private System system;
	
	public User() { }

	public User(String aName, String aMail, String aPassword, String aNick, System aSystem) {
	    	this.name = aName;
	    	this.mail = aMail;
	    	this.password = aPassword;
	    	this.nick = aNick;
    		this.system = aSystem;
	    }
	   
	abstract public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException;
	
	abstract public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException;
	
	abstract public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws UserException, ProjetcException ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}
}