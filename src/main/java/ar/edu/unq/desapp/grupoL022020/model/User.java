package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String mail;
	private String password;
	private String nick;
	private Integer points;
	private List<Donation> donations;
			
	public User(String aName, String aMail, String aPassword) {
			this.name = aName;
			this.mail = aMail;
			this.password = aPassword;
			this.points = 0;
			this.donations = new ArrayList<Donation>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}	
}
