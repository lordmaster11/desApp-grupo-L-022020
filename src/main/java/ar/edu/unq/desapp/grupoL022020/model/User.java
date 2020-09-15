package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class User {
	private String name;
	private String mail;
	private String password;
	private String nick;
	private Integer points;
	private List<Donation> donations;
	private Calendar lastDonationDate;

    private User(UserBuilder builder) {
    	this.name = builder.name;
    	this.mail = builder.mail;
    	this.password = builder.password;
    	this.nick = builder.nick;
    	this.points = builder.points;
    	this.donations = builder.donations;
    	this.lastDonationDate = builder.lastDonationDate;	
    }

	public String getName() {
		return name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNick() {
		return nick;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}
	
	public Calendar getLastDonationDate() {
		return lastDonationDate;
	}
	
	public void addDonation(Donation donation) {
		this.donations.add(donation);
	}
	
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
	
	public List<Donation> myTop10Donation() {
		List<Donation> donation= new ArrayList<Donation>();
		donation = donations.stream()
					.sorted(Comparator.comparing(Donation::getAmount).reversed())
					.collect(Collectors.toList());
		
		return donation.stream().limit(10).collect(Collectors.toList());
	}
	
    public static class UserBuilder {
    	private String name;
    	private String mail;
    	private String password;
    	private String nick;
    	private Integer points;
    	private List<Donation> donations;
    	private Calendar lastDonationDate;
    	
    	public UserBuilder(String aName, String aMail, String aPassword, String aNick) {
    		this.name = aName;
    		this.mail = aMail;
    		this.password = aPassword;
    		this.nick = aNick;
    		this.points = 0;
    		this.donations = new ArrayList<Donation>();
    		this.lastDonationDate = null;
    	}
    	
        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }
        
        public UserBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }
        
        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }  
        
        public UserBuilder withNick(String nick) {
            this.nick = nick;
            return this;
        } 
        
        public UserBuilder withPoints(Integer points) {
            this.points = points;
            return this;
        } 
        
        public UserBuilder withDonations (List<Donation> donations) {
            this.donations = donations;
            return this;
        } 
        
        public UserBuilder withLastDonationDate(Calendar lastDonationDate) {
            this.lastDonationDate = lastDonationDate;
            return this;
        } 
        
        public User build() {
            User user =  new User(this);
            return user;
        }        
    }
}