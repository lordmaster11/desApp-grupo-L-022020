package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
	
	Location locationProject;
	Integer factor = 1000;
	Integer percentageRequiredForClosing = 100; //between 50 and 100
	String fantasyName;
	Calendar projectStart;
	Calendar endOfProject;
	ArrayList<Donation> donations;
	
	public Project(Location location) {
		locationProject = location;	
		donations = new ArrayList<Donation>();
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
}