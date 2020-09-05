package ar.edu.unq.desapp.grupoL022020.model;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
	private List<Project> projects;
	private List<Location> locations;
	private List<User> users;
	
	public Administrator() {
		this.projects = new ArrayList<Project>();
		this.locations = new ArrayList<Location>();
		this.users = new ArrayList<User>();
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
