package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "donation")
public class Donation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private Calendar dateDonation;
	@Column
	private Integer amount;
	@Column
	private String comment;	
	
	@JsonManagedReference
	@ManyToOne(optional = true, cascade = CascadeType.PERSIST)
	@JoinColumn(name= "projectId", referencedColumnName = "id")
	private Project project;
	
	@JsonManagedReference
	@ManyToOne(optional = true, cascade = CascadeType.PERSIST)
	@JoinColumn(name= "userId", referencedColumnName = "id")
	private User user;
	
	public Donation () {}
	
	public Donation(User aUser, Project aProject, Integer anAmount, String aComment) {
		this.user = aUser;
		this.project = aProject;
		this.dateDonation = Calendar.getInstance();
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

	public void setDateDonation(Calendar dateDonation) {
		this.dateDonation = dateDonation;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setUser(User user) {
		this.user = user;
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
		if(lastDonationDateUser != null && (currentDate.get(Calendar.MONTH) == lastDonationDateUser.get(Calendar.MONTH)) 
				&& (currentDate.get(Calendar.YEAR) == lastDonationDateUser.get(Calendar.YEAR))){
			accumulatedPoints += 500;
		}	
		
		return accumulatedPoints;
	}
}