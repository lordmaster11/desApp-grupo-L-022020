package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserDonor extends User{
	private Integer points;
	private List<Donation> donations;
	private Calendar lastDonationDate;
	
	public UserDonor (String aName, String aMail, String aPassword, String aNick, System aSystem) {
		super(aName, aMail, aPassword, aNick, aSystem); 
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
		makeDonation(donation, project);
	}	
	
	public void makeDonation(Donation donation, Project project) throws UserException, ProjetcException {
		addDonation(donation);
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