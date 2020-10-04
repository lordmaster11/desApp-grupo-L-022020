package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UserAdmin  extends User{
	public UserAdmin() { }
	
	public UserAdmin (String aName, String aMail, String aPassword, String aNick) {
		 super(aName, aMail, aPassword, aNick);
		 super.setRole("Administrator");
	}
	
	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		
	//	super.getSystem().createNewProject(location, fantasyName, endOfProject);
	}
	
	public void setFactorInProjet(Project aProject, Integer factor) throws ProjetcException {
		aProject.setFactor(factor);
	}
	
	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws ProjetcException {
		aProject.setPercentageRequiredForClosing(percentageRequiredForClosing);
	}
}