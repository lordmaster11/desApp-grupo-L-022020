package ar.edu.unq.desapp.grupol022020.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "project")
@SequenceGenerator(name = "SEQ_PROJECT", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_PROJECT")
public class Project implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROJECT")
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "locationId", referencedColumnName = "id")
	private Location locationProject;
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
//	@OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "project")
//	@JoinColumn(name= "donationId", referencedColumnName = "id")
//	private List<Donation> donations;
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "project")
//	@JoinColumn(name= "userDonorId", referencedColumnName = "id")
//	private Set<User> donors;
	@Column
	private Calendar lastDonation;
	@Column
	private Integer donatedAmount;
	
	public Project() { }

	public Project(ProjectBuilder builder) {
		this.locationProject = builder.locationProject;
		this.factor = builder.factor;
		this.percentageRequiredForClosing = builder.percentageRequiredForClosing;
		this.fantasyName = builder.fantasyName;
		this.projectStart = builder.projectStart;
		this.endOfProject = builder.endOfProject;
//		this.donations = builder.donations;
//		this.donors = builder.donors;
		this.lastDonation = builder.lastDonation;
		this.donatedAmount = 0;
	}
	
	public void receiveDonation(Donation donation) throws ProjetcException {
		if(isDonationPossible(donation.getAmount())) {
				addTimeIfMissing();
				addDonation(donation);
//				addDonor(donation.getUser());
				donatedAmount += donation.getAmount();
		}else {
			throw new ProjetcException(
						"It is not possible to make a donation");
		}
	}

	private void addTimeIfMissing() {
		Calendar today = Calendar.getInstance();
		if(endOfProject.compareTo(today)<0) {
			today.add(Calendar.MONTH, 2);
			endOfProject = today;
		}
	}

	private boolean isDonationPossible(Integer amount) {
		return this.calculateMoneyNeeded() > donatedAmount + amount;
	}

	public Integer calculateMoneyNeeded() {
		return factor * locationProject.getPopulation();
	}
	
//	public void addDonor(User aUser) {
//		this.donors.add(aUser);
//	}
	
	public void addDonation(Donation aDonation) {
		this.setLastDonation(aDonation.getDateDonation());
//		this.donations.add(aDonation);
	}
	
	public Location getLocationProject() {
		return locationProject;
	}
	
//	public Set<User> getDonors() {
//		return donors;
//	}

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

	public Integer getDonatedAmount() {
		return donatedAmount;
	}
	
	public void setLastDonation(Calendar lastDonation) {
		this.lastDonation = lastDonation;
	}

 //   public List<Donation> getDonations() {
//		return donations;
//	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocationProject(Location locationProject) {
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

//	public void setDonations(List<Donation> donations) {
//		this.donations = donations;
//	}

//	public void setDonors(Set<User> donors) {
//		this.donors = donors;
//	}

	public void setDonatedAmount(Integer donatedAmount) {
		this.donatedAmount = donatedAmount;
	}

	public void setFactor(Integer factor) throws ProjetcException {
		if(factor < 0 || factor > 100000) {
            throw new ProjetcException(
                    "The project factor must be between 0 and 100000");
        }else {
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
//	private List<Donation> donations;
//	private Set<User> donors;
	private Calendar lastDonation;
	
	public ProjectBuilder(Location location) {
		this.locationProject = location;	
//		this.donations = new ArrayList<Donation>();
//		this.donors = new HashSet<User>();
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