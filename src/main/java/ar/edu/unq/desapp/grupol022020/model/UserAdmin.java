package ar.edu.unq.desapp.grupol022020.model;

import javax.persistence.Entity;

@Entity
public class UserAdmin extends User{
	public UserAdmin () {}
	
	public UserAdmin (String aName, String aMail, String aPassword, String aNick) {
		 super(aName, aMail, aPassword, aNick);
		 setRole("ROLE_ADMIN");
	}
	
	public Donation donate(Integer money, Project project, String comment) throws UserException, ProjetcException {	
		throw new UserException("Admin user cannot donate");
	}
}