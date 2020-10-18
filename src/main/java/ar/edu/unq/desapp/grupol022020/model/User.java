package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@Table(name = "user")
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
	@Column
	private Calendar lastDonationDate;
	@Column
	private Integer points;
//	@JsonManagedReference
	@JsonBackReference
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
	public List<Donation> donations = new ArrayList<>();
	
	public User() { }

	public User(String aName, String aMail, String aPassword, String aNick) {
	    this.name = aName;
	    this.mail = aMail;
	    this.password = aPassword;
	    this.nick = aNick; 
	    this.points = 0;
    	this.donations = new ArrayList<Donation>();
	}
	   
	abstract public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException;
	
	abstract public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException;
	
	abstract public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws UserException, ProjetcException ;
	
	abstract public Donation donate(Integer money, Project project, String comment) throws UserException, ProjetcException;
	
	public void addPoint(Integer addPoints) {
		this.points += addPoints;
	}
	
	public void addDonation(Donation onDonation) {
		this.donations.add(onDonation);
	}

	public Integer totalDonation() {
		Integer totalDonated = donations.stream()
							.mapToInt(Donation::getAmount).sum();
		return totalDonated;
	}
	
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
	public List<Donation> getDonations() {
		return donations;
	}
	
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public Calendar getLastDonationDate() {
		return lastDonationDate;
	}
	
	public void setLastDonationDate(Calendar lastDonationDate) {
		this.lastDonationDate = lastDonationDate;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}