package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name = "user")
@MappedSuperclass
public abstract class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column
	private String name;
	@Column
	private String mail;
	@Column
	private String password;
	@Column
	private String nick;
	@Column
	private String role;
	
	public User() { }

	public User(String aName, String aMail, String aPassword, String aNick) {
	    this.name = aName;
	    this.mail = aMail;
	    this.password = aPassword;
	    this.nick = aNick;   		
	}
	   
	abstract public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException;
	
	abstract public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException;
	
	abstract public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws UserException, ProjetcException ;
	
	abstract public void donate(Integer money, Project project, String comment) throws UserException, ProjetcException;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}