package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.model.UserException;

class DonationTest {	
	@Test
	public void createDonation(){ 			
		UserDonor user = mock(UserDonor.class);
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
		Calendar date = new GregorianCalendar(2020, Calendar.AUGUST,30);

		Donation donation= new Donation();
		
		donation.setUser(user);
		donation.setProject(project);
		donation.setComment("first donation");
		donation.setAmount(5000);
		donation.setDateDonation(date);
		donation.setId(11);
		
		assertEquals(donation.getUser(), user);		
		assertEquals(donation.getProject(), project);
		assertEquals(donation.getComment(), "first donation");
		assertEquals(donation.getAmount(), 5000);
		assertEquals(donation.getDateDonation(), date);
		assertEquals(donation.getId(), 11);
	}
	
	@Test
	public void donateInAProjectWhithLessThan1000(){ 	
		UserDonor aUser = mock(UserDonor.class);
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
		Donation donation= new Donation(aUser, project, 500, "First donation");
		aUser.addDonation(donation);
				
		assertEquals(donation.getUser(), aUser);		
		assertEquals(donation.getProject(), project);
		assertEquals(donation.getAmount(), 500);
		assertEquals(donation.getComment(), "First donation");
	}
	
	@Test
	public void donationInDifferentYearAndMonth() throws UserException, ProjetcException{ 
		UserDonor user = mock(UserDonor.class);
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
		
		user.setLastDonationDate(new GregorianCalendar(2011, Calendar.FEBRUARY,11));

		Donation donation= new Donation(user, project, 1500, "First donation");
	
		assertEquals(donation.calculatePoints(user, project), 1500);
	}
}