package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;

class DonationTest {	
	@Test
	public void donateInAProjectWhithLessThan1000(){ 	
		UserDonor aUser = mock(UserDonor.class);
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
		Calendar date = Calendar.getInstance();
		Donation donation= new Donation(aUser, project, 500, "First donation");
		aUser.addDonation(donation);
				
		assertEquals(donation.getUser(), aUser);		
		assertEquals(donation.getProject(), project);
		assertEquals(donation.getDateDonation(), date);
		assertEquals(donation.getAmount(), 500);
		assertEquals(donation.getComment(), "First donation");
	}
}