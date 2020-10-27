package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name= "locationId", referencedColumnName = "id")
	private Location locationProject;
	
	@JsonBackReference
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Donation> donations = new ArrayList<>();
		
	@Column
	private Integer factor;
	@Column
	private Integer percentageRequiredForClosing; //between 50 and 100
	@Column
	private String fantasyName;
	@Column
	private Calendar projectStart;
	@Column
	private Calendar endOfProject;
	@Column
	private Calendar lastDonation;
	@Column
	private Integer donatedAmount;
	@Column
	private Boolean isOpen;
	@Column
	private Integer MoneyNeeded;
	@Column
	private Integer numberOfDonors;

	public Project() { }

	public Project(ProjectBuilder builder) {
		this.locationProject = builder.locationProject;
		this.factor = builder.factor;
		this.percentageRequiredForClosing = builder.percentageRequiredForClosing;
		this.fantasyName = builder.fantasyName;
		this.projectStart = builder.projectStart;
		this.endOfProject = builder.endOfProject;
		this.donations = new ArrayList<Donation>();
		this.lastDonation = builder.lastDonation;
		this.donatedAmount = 0;
		this.isOpen = true;
		this.MoneyNeeded = factor * locationProject.getPopulation();
		this.numberOfDonors = 0;
		this.locationProject.setProjectAssociated(true);
		}
	
	public void receiveDonation(Donation donation) throws ProjetcException {
		if(!isOpen) {
			throw new ProjetcException("Project is close");
		}
		if(this.getDonatedAmount()+donation.getAmount() == this.getMoneyNeeded()) {
			this.setIsOpen(false);
		}
		if(isDonationPossible(donation.getAmount())){
			if(isNewDonor(donation.getUser())) {
				numberOfDonors += 1;
			}
			addTimeIfMissing();
			addDonation(donation);
			donatedAmount += donation.getAmount();
			
		}else{
			Integer amountMax = this.getMoneyNeeded() - this.getDonatedAmount();
			throw new ProjetcException(
					"It is not possible to make a donation. The maximum amount "
					+ "possible is " + amountMax.toString());
		}		
	}

	public boolean isNewDonor(User user) {
		for(Donation donation:this.donations) {
			if(donation.getUser() == user) {
				return false;
			}
		}	
		return true;
	}

	private void addTimeIfMissing() {
		Calendar today = Calendar.getInstance();
		if(endOfProject != null && endOfProject.compareTo(today)<0) {
			today.add(Calendar.MONTH, 2);
			endOfProject = today;
		}
	}

	private boolean isDonationPossible(Integer amount) {
		return this.calculateMoneyNeeded() >= donatedAmount + amount;
	}

	public Integer calculateMoneyNeeded() {
		return factor * locationProject.getPopulation();
	}

	public void addDonation(Donation aDonation) {
	//	this.setLastDonation(aDonation.getDateDonation());
		this.donations.add(aDonation);
	}
	
	public Location getLocationProject() {
		return locationProject;
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

	public Integer getNumberOfDonors() {
		return numberOfDonors;
	}

	public void setNumberOfDonors(Integer numberOfDonors) {
		this.numberOfDonors = numberOfDonors;
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

	public Integer getDonatedAmount() {
		return donatedAmount;
	}
	
	public void setLastDonation(Calendar lastDonation) {
		this.lastDonation = lastDonation;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void setLocationProject(Location locationProject) {
		this.setDonatedAmount(this.getFactor() * locationProject.getPopulation());
		this.locationProject = locationProject;
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

	public void setDonatedAmount(Integer donatedAmount) {
		this.donatedAmount = donatedAmount;
	}
	
	public Integer getMoneyNeeded() {
		return MoneyNeeded;
	}

	public void setMoneyNeeded(Integer moneyNeeded) {
		MoneyNeeded = moneyNeeded;
	}

	public void setFactor(Integer factor) throws ProjetcException {
		if(factor < 0 || factor > 100000) {
            throw new ProjetcException(
                    "The project factor must be between 0 and 100000");
        }else {
        	this.setDonatedAmount(factor * this.getLocationProject().getPopulation());
        	this.factor = factor;
		}
	}

	public void setPercentageRequiredForClosing(Integer percentageRequiredForClosing) throws ProjetcException {
		if(percentageRequiredForClosing > 100 ||percentageRequiredForClosing  < 50 ){
            throw new ProjetcException(
            		"The percentage required to close the project must be between 50 and 100 percent");
        }else {
        	this.percentageRequiredForClosing = percentageRequiredForClosing;
        }	
	}

public static class ProjectBuilder {
	private Location locationProject;
	private Integer factor;
	private Integer percentageRequiredForClosing; //between 50 and 100
	private String fantasyName;
	private Calendar projectStart;
	private Calendar endOfProject;
	private Calendar lastDonation;
	
	public ProjectBuilder(Location location) {
		this.locationProject = location;	
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

    public Project build() throws ProjetcException {
    	Project project =  new Project(this);
    	validateProjectObject(project);
        return project;
    }

	private void validateProjectObject(Project project) throws ProjetcException {
		if(project.percentageRequiredForClosing > 100 ||project.percentageRequiredForClosing  < 50 ){
            throw new ProjetcException(
                    "The percentage required to close the project must be between 50 and 100 percent");
            }
		if(project.factor < 0 || project.factor > 100000) {
            throw new ProjetcException(
                    "The project factor must be between 0 and 100000");
            }
		} 
	}	
}