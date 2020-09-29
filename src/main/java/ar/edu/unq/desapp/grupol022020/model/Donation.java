package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

public class Donation {
	private User user;
	private Project project;
	private Calendar dateDonation;
	private Integer amount;
	private String comment;

	public Donation(User aUser, Project aProject, Calendar aDate, Integer anAmount, String aComment) {
		this.user = aUser;
		this.project = aProject;
		this.dateDonation = aDate;
		this.amount = anAmount;
		this.comment = aComment;
	}	

	public User getUser() {
		return user;
	}

	public Project getProject() {
		return project;
	}

	public Calendar getDateDonation() {
		return dateDonation;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public String getComment() {
		return comment;
	}

	public Integer bonus() {
		return 500;
	}
	
	public Integer calculatePoints(UserDonor user, Project aProject) throws UserException {
		Integer accumulatedPoints = 0;
		Calendar currentDate = Calendar.getInstance();
		Calendar lastDonationDateUser = user.getLastDonationDate();
		Integer population = aProject.getLocationProject().getPopulation();
		
		if((amount <= 0)){
			throw new UserException("The amount of the donation cannot be less than or equal to 0");
	    }
		if(amount > 1000){
			accumulatedPoints +=  amount;
		}	
		if(population < 2000){
			accumulatedPoints += amount*2;
		}	
		if(lastDonationDateUser != null && currentDate.get(Calendar.MONTH) == lastDonationDateUser.get(Calendar.MONTH) 
				&& currentDate.get(Calendar.YEAR) == lastDonationDateUser.get(Calendar.YEAR)){
			accumulatedPoints += bonus();
		}	
		
		return accumulatedPoints;
	}
}