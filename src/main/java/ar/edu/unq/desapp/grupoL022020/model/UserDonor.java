package ar.edu.unq.desapp.grupoL022020.model;

import java.util.Calendar;

public class UserDonor extends User{

	public UserDonor (String aName, String aMail, String aPassword, String aNick, System aSystem) {
		 super(aName, aMail, aPassword, aNick, aSystem); 
		 super.setProfile("Donor");
	}

	public void createProject(Location location, String fantasyName,
			Calendar endOfProject) throws UserException, ProjetcException{
		throw new UserException("Your profile does not allow you to create projects");
	}

	public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException {
		throw new UserException("Your profile does not allow you to modified projects");
	}

	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing)
			throws UserException, ProjetcException{
		throw new UserException("Your profile does not allow you to modified projects");
	}	

}
