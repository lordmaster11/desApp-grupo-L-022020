package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UserDonor extends User {
	@Column
	private Calendar lastDonationDate;
	@Column
	private Integer points;
	@JsonManagedReference
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Donation> donations = new ArrayList<>();

	public UserDonor() { }
	
	public UserDonor (String aName, String aMail, String aPassword, String aNick) {
		super(aName, aMail, aPassword, aNick); 
		super.setRole("ROLE_USER");
		this.setLastDonationDate(null);
    	this.points = 0;   
    	this.donations = new ArrayList<Donation>();
	}

	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		throw new UserException("User cannot create projects");
	}

	public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException {
		throw new UserException("User cannot update projects");
	}

	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing)
			throws UserException, ProjetcException{
		throw new UserException("User cannot update projects");
	}
	
	public void donate(Integer money, Project project, String comment) throws UserException, ProjetcException {		
		Donation donation = new Donation(this, project, Calendar.getInstance(), money, comment);	
		makeDonation(donation, project);
	}	
	
	private void makeDonation(Donation donation, Project project) throws UserException, ProjetcException {
		this.addDonation(donation);
		this.addPoint(donation.calculatePoints(this, project));
		this.setLastDonationDate(donation.getDateDonation());
		project.receiveDonation(donation);
	}
	
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
}