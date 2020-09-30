package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//@Entity
//@SequenceGenerator(name = "SEQ_USER_ADMIN", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_USER_ADMIN")
public class UserAdmin  extends User{
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ADMIN")
	private Integer id;

	public UserAdmin (String aName, String aMail, String aPassword, String aNick,  System aSystem) {
		 super(aName, aMail, aPassword, aNick, aSystem);
		 super.setRole("Administrator");
	}
	
	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		
		super.getSystem().createNewProject(location, fantasyName, endOfProject);
	}
	
	public void setFactorInProjet(Project aProject, Integer factor) throws ProjetcException {
		aProject.setFactor(factor);
	}
	
	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws ProjetcException {
		aProject.setPercentageRequiredForClosing(percentageRequiredForClosing);
	}
}