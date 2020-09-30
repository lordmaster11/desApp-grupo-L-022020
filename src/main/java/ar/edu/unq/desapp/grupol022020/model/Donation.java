package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

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

//@Entity
//@Table(name = "donation")
//@SequenceGenerator(name = "SEQ_DONATION", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_DONATION")
public class Donation {
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DONATION")
	private Integer id;
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name= "userDonorId", referencedColumnName = "id")
//  @ManyToOne(optional = false, cascade = CascadeType.ALL)
	private User user;
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name= "projectId", referencedColumnName = "id")
	private Project project;
//	@Column
	private Calendar dateDonation;
//	@Column
	private Integer amount;
//	@Column
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setDateDonation(Calendar dateDonation) {
		this.dateDonation = dateDonation;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setComment(String comment) {
		this.comment = comment;
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