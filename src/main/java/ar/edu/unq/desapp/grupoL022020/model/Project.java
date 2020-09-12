package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	private Location locationProject;
	private Integer factor;
	private Integer percentageRequiredForClosing; //between 50 and 100
	private String fantasyName;
	private Calendar projectStart;
	private Calendar endOfProject;
	private List<Donation> donations;  //Hay q ver si es mejor tener donaciones, donantes o ambos; o sino buscar dentro de donaciones a los donates!!!!!!!
	private List<User> donors;
	private Calendar lastDonation;

	public Project(ProjectBuilder builder) {
		this.locationProject = builder.locationProject;
		this.factor = builder.factor;
		this.percentageRequiredForClosing = builder.percentageRequiredForClosing;
		this.fantasyName = builder.fantasyName;
		this.projectStart = builder.projectStart;
		this.endOfProject = builder.endOfProject;
		this.donations = builder.donations;
		this.donors = builder.donors;
		this.lastDonation = builder.lastDonation;
	}

	public Integer calculateMoneyNeeded() {
		return factor * locationProject.getPopulation();
	}
	
	public void addDonor(User aUser) {
		this.donors.add(aUser);
	}
	
	public void addDonotion(Donation aDonation) {
		this.setLastDonation(aDonation.getDateDonation());
		this.donations.add(aDonation);
	}
	
	public Location getLocationProject() {
		return locationProject;
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

	public Calendar getLastDonation() {
		return lastDonation;
	}
	
	public void setLastDonation(Calendar lastDonation) {
		this.lastDonation = lastDonation;
	}

    public List<Donation> getDonations() {
		return donations;
	}

public static class ProjectBuilder {
	private Location locationProject;
	private Integer factor;
	private Integer percentageRequiredForClosing; //between 50 and 100
	private String fantasyName;
	private Calendar projectStart;
	private Calendar endOfProject;
	private List<Donation> donations;
	private List<User> donors;
	private Calendar lastDonation;
	
	public ProjectBuilder(Location location) {
		this.locationProject = location;	
		this.donations = new ArrayList<Donation>();
		this.donors = new ArrayList<User>();
		this.factor = 1000;
		this.percentageRequiredForClosing = 100;
		this.projectStart = Calendar.getInstance();
		this.lastDonation = Calendar.getInstance();
	}
	
    public ProjectBuilder withFactor(Integer factor) {
        this.factor = factor;
        return this;
    }
    
    public ProjectBuilder withPercentageRequiredForClosing(Integer percentageRequiredForClosing) {
        this.percentageRequiredForClosing = percentageRequiredForClosing;
        return this;
    }
    
    public ProjectBuilder withFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
        return this;
    }
    
    public ProjectBuilder withProjectStart(Calendar projectStart) {
        this.projectStart = projectStart;
        return this;
    }
    
    public ProjectBuilder withEndOfProject(Calendar endOfProject) {
        this.endOfProject = endOfProject;
        return this;
    }

    public Project build() throws ProjetException {
    	Project project =  new Project(this);
    	validateProjectObject(project);
        return project;
    }

	private void validateProjectObject(Project project) throws ProjetException {
		if(project.percentageRequiredForClosing > 100 ||project.percentageRequiredForClosing  < 50 ){
            throw new ProjetException(
                    "The percentage required to close the project must be between 50 and 100 percent");
            }
		if(project.factor < 0 || project.factor >100000) {
            throw new ProjetException(
                    "The project factor must be between 0 and 100000");
            }
	} 
	}	
}