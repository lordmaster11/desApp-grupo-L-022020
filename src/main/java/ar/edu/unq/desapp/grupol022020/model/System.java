package ar.edu.unq.desapp.grupol022020.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class System {
	/*
	private List<Project> projects;
	private List<Location> locations;
	private List<UserDonor> users;
	
	public System() {
		this.projects = new ArrayList<Project>();
		this.locations = new ArrayList<Location>();
		this.users = new ArrayList<UserDonor>();
	}

	public Project createNewProject(Location location, String nameFantasy, Calendar endOfProject) throws ProjetcException {
		Project newProject = new Project.ProjectBuilder(location)
										.withFantasyName(nameFantasy)
										.withEndOfProject(endOfProject)
										.build();
		projects.add(newProject);
		return newProject;
	}
	
	public User createUserDonor(String aName, String aMail, String aPassword, String aNick) {
		UserDonor newUser = new UserDonor(aName, aMail, aPassword, aNick);
		this.addUser(newUser);
		return newUser;
	}
	
	public User createUserAdmin(String aName, String aMail, String aPassword, String aNick) {
		User newUser = new UserAdmin(aName, aMail, aPassword, aNick);
		//this.addUser(newUser);
		return newUser;
	}

	public List<Donation> best10Donations() {
		List<Donation> allDonations= new ArrayList<Donation>();
		for(UserDonor user:users) {
			allDonations.addAll(user.getDonations());
		}
		return allDonations.stream()
						   .sorted(Comparator.comparing(Donation::getAmount).reversed())
						   .limit(10)
						   .collect(Collectors.toList());
	}
	
	public List<Project> donationFreeLocationsForLonger() {
		return projects.stream()
				       .sorted(Comparator.comparing(Project::getLastDonation))
				       .limit(10)
					   .collect(Collectors.toList());
	}
	
	public void addUser(UserDonor user) {
		users.add(user);
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public List<UserDonor> getUser() {
		return users;
	}*/
}