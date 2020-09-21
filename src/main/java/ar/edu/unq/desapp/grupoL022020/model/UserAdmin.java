package ar.edu.unq.desapp.grupoL022020.model;

import java.util.Calendar;


public class UserAdmin  extends User{

	public UserAdmin (String aName, String aMail, String aPassword, String aNick,  System aSystem) {
		 super(aName, aMail, aPassword, aNick, aSystem);
		 super.setProfile("Administrator");
	}
	
	public void createProject(Location location, String fantasyName,
							  Calendar endOfProject) 
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
