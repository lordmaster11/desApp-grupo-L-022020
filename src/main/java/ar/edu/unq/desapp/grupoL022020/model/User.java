package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class User{
	
	private String name;
	private String mail;
	private String password;
	private String nick;
	private Integer points;
	private List<Donation> donations;
	private Calendar lastDonationDate;
	private String profile;
	private System system;

	protected User(String aName, String aMail, String aPassword, String aNick, System aSystem) {
	    	this.name = aName;
	    	this.mail = aMail;
	    	this.password = aPassword;
	    	this.nick = aNick;
    		this.points = 0;
    		this.donations = new ArrayList<Donation>();
    		this.lastDonationDate = null;
    		this.system = aSystem;
	    }
	   
	abstract public void createProject(Location location, String fantasyName,
									   Calendar endOfProject
									  ) throws UserException, ProjetcException;
	abstract public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException;
	abstract public void setPercentageRequiredForClosingInProjet
							(Project aProject, Integer percentageRequiredForClosing) 
							throws UserException, ProjetcException ;
	
	public void donate(Integer money, Project aProject) throws UserException{
		Integer accumulatedPoints = this.points;
		Calendar currentDate = Calendar.getInstance();
		Integer population = aProject.getLocationProject().getPopulation();
		
		if((money <= 0)){
			throw new UserException("The donation cannot be less than 0");
	    }
		if(money > 1000){
			accumulatedPoints = money;
		}	
		if(population < 2000){
			accumulatedPoints += money*2;
		}	
		if(this.lastDonationDate != null && currentDate.get(Calendar.MONTH) == lastDonationDate.get(Calendar.MONTH)){
			accumulatedPoints += 500;
		}	
		this.lastDonationDate = currentDate;
		this.points += accumulatedPoints;
		Donation donation = new Donation(this, aProject, currentDate, money);
		this.addDonation(donation);
		aProject.addDonotion(donation);
		if(!aProject.getDonors().contains(this)){
			aProject.addDonor(this);
		}
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


	public void setProfile(String profile) {
		this.profile = profile;
	}

	public System getSystem() {
		return system;
	}
 
}