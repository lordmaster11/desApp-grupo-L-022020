package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Entity;

@Entity
public class UserAdmin extends User{
	
	public UserAdmin () {}
	
	public UserAdmin (String aName, String aMail, String aPassword, String aNick) {
		 super(aName, aMail, aPassword, aNick);
		 setRole("ROLE_ADMIN");
	}

	public void createProject(Location location, String fantasyName, Calendar endOfProject) 
			throws UserException, ProjetcException{
		/*SessionFactory sessionFactory = ProjectService.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Project project = new Project.ProjectBuilder(location)
									 .withFantasyName(fantasyName)
									 .withEndOfProject(endOfProject)
									 .build();
		session.save(project);
		sessionFactory.close();*/
	}
	
	public void setFactorInProjet(Project aProject, Integer factor) throws ProjetcException {
		aProject.setFactor(factor);
	}
	
	public void setPercentageRequiredForClosingInProjet(Project aProject, Integer percentageRequiredForClosing) 
			throws ProjetcException {
		aProject.setPercentageRequiredForClosing(percentageRequiredForClosing);
	}
	
	public Donation donate(Integer money, Project project, String comment) throws UserException, ProjetcException {	
		throw new UserException("Admin user cannot donate");
	}
}