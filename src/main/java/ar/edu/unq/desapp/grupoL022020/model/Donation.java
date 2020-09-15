package ar.edu.unq.desapp.grupoL022020.model;

import java.util.Calendar;

public class Donation {
	private User user;
	private Project project;
	private Calendar dateDonation;
	private Integer amount;

	public Donation(User aUser, Project aProject, Calendar aDate, Integer anAmount) {
		this.user = aUser;
		this.project = aProject;
		this.dateDonation = aDate;
		this.amount = anAmount;
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
}