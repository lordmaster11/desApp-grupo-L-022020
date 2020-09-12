package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Administrator {
	private List<Project> projects;
	private List<Location> locations;
	private List<User> users;
	
	public Administrator() {
		this.projects = new ArrayList<Project>();
		this.locations = new ArrayList<Location>();
		this.users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	//Tarea de Usuario con rol gestor de Proyectos // User abstracto?
	public void createNewProject(Location location) throws ProjetException {
		Project newProject = new Project.ProjectBuilder(location).build();
		projects.add(newProject);
	}
	
	public List<User> top10DonorUsers() {
		return users.stream()
				.sorted(Comparator.comparing(User::totalDonation).reversed())
				.limit(10)
				.collect(Collectors.toList());
	}
	
	public List<Donation> best10Donations() {
		List<Donation> allDonations= new ArrayList<Donation>();
		for(User user:users) {
			allDonations.addAll(user.myTop10Donation());
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
		
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<Location> getLocations() {
		return locations;
	}
}
