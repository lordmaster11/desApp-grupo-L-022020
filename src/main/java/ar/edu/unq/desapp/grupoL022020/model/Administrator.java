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
	public List<User> top10DonorUsers() {
		List<User> user= new ArrayList<User>();
		user = users.stream()
				.sorted(Comparator.comparing(User::totalDonation).reversed())
				.collect(Collectors.toList());

		return user.stream().limit(10).collect(Collectors.toList());
	}
	public List<User> bestDonors() {
		List<User> user= new ArrayList<User>();
		user = users.stream()
				.sorted(Comparator.comparing(User::totalDonation).reversed())
				.collect(Collectors.toList());

		return user.stream().limit(10).collect(Collectors.toList());
	}
		
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
