package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	private Location locationProject;
	private Integer factor = 1000;
	private Integer percentageRequiredForClosing = 100; //between 50 and 100
	private String fantasyName;
	private Calendar projectStart;
	private Calendar endOfProject;
//	private List<Donation> donations;  Hay q ver si es mejor tener donaciones, donantes o ambos; o sino buscar dentro de donaciones a los donates!!!!!!!
	private List<User> donors;

	public Project(Location location) {
		locationProject = location;	
//		donations = new ArrayList<Donation>();
		this.donors = new ArrayList<User>();
	}
	
	public Integer calculateMoneyNeeded() {
		return factor * locationProject.getPopulation();
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public void setPercentageRequiredForClosing(Integer percentageRequiredForClosing) {
		this.percentageRequiredForClosing = percentageRequiredForClosing;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public void setProjectStart(Calendar projectStart) {
		this.projectStart = projectStart;
	}

	public void setEndOfProject(Calendar endOfProject) {
		this.endOfProject = endOfProject;
	}
	
	public Location getLocationProject() {
		return locationProject;
	}

	public void addDonor(User aUser) {
		this.donors.add(aUser);
	}

	public List<User> getDonors() {
		return donors;
	}

	public Integer getFactor() {
		return factor;
	}

	public Integer getPercentageRequiredForClosing() {
		return percentageRequiredForClosing;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public Calendar getProjectStart() {
		return projectStart;
	}

	public Calendar getEndOfProject() {
		return endOfProject;
	}
}