package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.User;

public class UserTest {
	@Test
	public void newUser(){ 
		User aUser = new User ("Juan", "juan@gmail.com", "1234");
		assertTrue(aUser.getName().equals("Juan"));	
		assertTrue(aUser.getMail().equals("juan@gmail.com"));
	}
	
	@Test
	public void donateInAProjectWhithLessThan$1000(){ 	
		User aUser = new User ("Pepe", "pepe@gmail.com", "1234");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(500, project);
		assertTrue(aUser.getPoints().equals(0));
		assertTrue(aUser.getDonations().size()==(1));		
	}
	
	@Test
	public void donateInAProjectWhithMoreThan$1000(){ 	
		User aUser = new User ("Pepe", "pepe@gmail.com", "1234");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(1500, project);
		assertTrue(aUser.getPoints().equals(1500));
		assertTrue(aUser.getDonations().size()==(1));		
	}
	
	@Test
	public void donateInAProjectInATownWhithLessThan2000Populations(){ 	
		User aUser = new User ("Esteban", "esteban@gmail.com", "1234");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(500, project);
		assertTrue(aUser.getPoints().equals(1000));
		assertTrue(aUser.getDonations().size()==(1));		
	}
	
	@Test
	public void donateInAProjectInATownWhithLessThan2000PopulationsAndMoreThan$1000(){ 	
		User aUser = new User ("Coco", "coquito@gmail.com", "1234");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(2000, project);
		assertTrue(aUser.getPoints().equals(6000));
		assertTrue(aUser.getDonations().size()==(1));		
	}
}
