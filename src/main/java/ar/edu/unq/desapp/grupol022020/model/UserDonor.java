package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

public class UserDonor extends User{
	public UserDonor (String aName, String aMail, String aPassword, String aNick, System aSystem) {
		 super(aName, aMail, aPassword, aNick, aSystem); 
		 super.setRole("Donor");
	}

	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		throw new UserException("Tu perfil no te permite crear proyectos");
	}

	public void setFactorInProjet(Project aProject, Integer factor) throws UserException, ProjetcException {
		throw new UserException("Tu perfil no te permite modificar proyectos");
	}

	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing)
			throws UserException, ProjetcException{
		throw new UserException("Tu perfil no te permite modificar proyectos");
	}
}