package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Donation;
import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.User;

public class UserTest {
	@Test
	public void newUser(){ 
		User aUser = new User ("Juan", "juan@gmail.com", "1234", "Master");
		
		assertEquals(aUser.getName(), "Juan");		
		assertEquals(aUser.getMail(), "juan@gmail.com");
		assertEquals(aUser.getPassword(), "1234");
		assertEquals(aUser.getNick(), "Master");
		assertEquals(aUser.getLastDonationDate(), null);		
	}
	
	@Test
	public void settersUser(){ 
		User aUser = new User ("Juan", "juan@gmail.com", "1234", "Master");
		
		aUser.setName("Juan Carlos");
		aUser.setMail("juanca@gmail.com");
		aUser.setPassword("12345");
		aUser.setNick("Juanca");
		aUser.setPoints(10000);
		
		assertEquals(aUser.getName(), "Juan Carlos");		
		assertEquals(aUser.getMail(), "juanca@gmail.com");
		assertEquals(aUser.getPassword(), "12345");
		assertEquals(aUser.getNick(), "Juanca");
		assertEquals(aUser.getPoints(), 10000);
	}
	
	@Test
	public void donateInAProjectWhithLessThan$1000(){ 	
		User aUser = new User ("Pepe", "pepe@gmail.com", "1234", "Argento");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(500, project);
				
		assertEquals(aUser.getPoints(), 0);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 500);
	}
	
	@Test
	public void donateInAProjectWhithMoreThan$1000(){ 	
		User aUser = new User ("Pepe", "pepe@gmail.com", "1234", "Argento");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(1500, project);
				
		assertEquals(aUser.getPoints(), 1500);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 1500);
	}
	
	@Test
	public void donateInAProjectInATownWhithLessThan2000Populations(){ 	
		User aUser = new User ("Esteban", "esteban@gmail.com", "1234", "Kito");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(500, project);
				
		assertEquals(aUser.getPoints(), 1000);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 500);
	}
	
	@Test
	public void donateInAProjectInATownWhithLessThan2000PopulationsAndMoreThan$1000(){ 	
		User aUser = new User ("Francisco", "fran@gmail.com", "1234", "pancho");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(2000, project);
		
		assertEquals(aUser.getPoints(), 6000);
		assertEquals(aUser.getDonations().size(), 1);		
		assertEquals(aUser.totalDonation(), 2000);
	}
	
	@Test
	public void donateInTwoAProjectsInTheSameMonth(){ 	
		User aUser = new User ("Fede", "fede@gmail.com", "1234", "facha");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(2000, project);
		aUser.donate(1500, project2);

		assertEquals(aUser.getPoints(), 8000);		
		assertEquals(aUser.getDonations().size(), 2);
		assertEquals(aUser.totalDonation(), 3500);
	}
	
	@Test
	public void donateInTwoAProjectsIndifferentMonth(){ 	
		User aUser = new User ("Fede", "fede@gmail.com", "1234", "facha");

		aUser.setLastDonationDate(new GregorianCalendar(2020, Calendar.APRIL,11));
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(1500, project2);

		assertEquals(aUser.getPoints(), 1500);
		assertEquals(aUser.totalDonation(), 1500);
	}
}
