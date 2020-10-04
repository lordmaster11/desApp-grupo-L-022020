package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UserDonor extends User{
	@Column
	private Integer points;
    @OneToMany(cascade = CascadeType.ALL)
	private List<Donation> donations;
	@Column
	private Calendar lastDonationDate;
	
	public UserDonor() { }
	
	public UserDonor (String aName, String aMail, String aPassword, String aNick) {
		super(aName, aMail, aPassword, aNick); 
		super.setRole("Donor");
		this.points = 0;
 		this.donations = new ArrayList<Donation>();
 		this.lastDonationDate = null;
	}

	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		throw new UserException("Tu perfil no te permite crear proyectos");
	}

	public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException {
		throw new UserException("Tu perfil no te permite modificar proyectos");
	}

	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing)
			throws UserException, ProjetcException{
		throw new UserException("Tu perfil no te permite modificar proyectos");
	}
	
	public void donate(Integer money, Project project, String comment) throws UserException, ProjetcException {		
		Donation donation = new Donation(this, project, Calendar.getInstance(), money, comment);	
		addDonation(donation);
		makeDonation(donation, project);
	}	
	
	public void makeDonation(Donation donation, Project project) throws UserException, ProjetcException {
		sumPoints(donation.calculatePoints(this, project));
		setLastDonationDate(donation.getDateDonation());
		project.receiveDonation(donation);
	}
		
	private void sumPoints(Integer accumulatedPoints) {
		this.points += accumulatedPoints;
	}

	public Integer totalDonation() {
		Integer totalDonated = donations.stream()
							.mapToInt(Donation::getAmount).sum();
		return totalDonated;
	}
	
	public Integer getPoints() {
		return points;
	}

	public List<Donation> getDonations() {
		return donations;
	}
	
	public void addDonation(Donation donation) {
		this.donations.add(donation);
	}
	
	public void setLastDonationDate(Calendar lastDonationDate) {
			this.lastDonationDate = lastDonationDate;
	}

	public Calendar getLastDonationDate() {
		return lastDonationDate;
	}
}