package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UserDonor extends User {
	
	public UserDonor() { }
	
	public UserDonor (String aName, String aMail, String aPassword, String aNick) {
		super(aName, aMail, aPassword, aNick); 
		super.setRole("Donor");
		super.setLastDonationDate(null);
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
	
	private void makeDonation(Donation donation, Project project) throws UserException, ProjetcException {
		super.addDonation(donation);
		super.addPoint(donation.calculatePoints(this, project));
		super.setLastDonationDate(donation.getDateDonation());
		project.receiveDonation(donation);
	}
}
