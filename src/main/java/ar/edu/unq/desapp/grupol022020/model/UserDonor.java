package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class UserDonor extends User {
	public UserDonor() { }
	
	public UserDonor (String aName, String aMail, String aPassword, String aNick) {
		super(aName, aMail, aPassword, aNick); 
		this.setLastDonationDate(null);
		super.setRole("ROLE_USER");
		super.setPoints(0);
		super.setDonations(new ArrayList<Donation>());
	}
	
	public Donation donate(Integer money, Project project, String comment) throws UserException, ProjetcException {		
		Donation donation = new Donation(this, project, money, comment);	
		makeDonation(donation, project);
		return donation;
	}	
	
	private void makeDonation(Donation donation, Project project) throws UserException, ProjetcException {
		this.addDonation(donation);
		this.addPoint(donation.calculatePoints(this, project));
		this.setLastDonationDate(donation.getDateDonation());
		project.receiveDonation(donation);
	}
}