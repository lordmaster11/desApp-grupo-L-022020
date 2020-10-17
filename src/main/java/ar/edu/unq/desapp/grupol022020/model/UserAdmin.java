package ar.edu.unq.desapp.grupol022020.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@Embeddable
//@PrimaryKeyJoinColumn(name = "id")
public class UserAdmin  extends User{
/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="id")
//	private Integer id;
	
	@Column
	private String role;
	
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
	
	public void donate(Integer money, Project project, String comment) throws UserException, ProjetcException {	
		throw new UserException("Admin user cannot donate");
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}