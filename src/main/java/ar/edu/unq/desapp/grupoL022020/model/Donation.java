package ar.edu.unq.desapp.grupoL022020.model;

import java.util.Calendar;

public class Donation {
	Calendar dateDonation;
	Integer amount;
	User user;
	
	public Donation(User myUser, Integer myAmount){
		dateDonation = Calendar.getInstance();
		amount = myAmount;
		user = myUser;
	}
	
	public Integer getAmount() {
		return amount;
	}
	

}
