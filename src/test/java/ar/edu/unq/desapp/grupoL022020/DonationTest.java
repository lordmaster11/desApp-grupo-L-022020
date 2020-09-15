package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Donation;
import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.User;

class DonationTest {	
	@Test
	public void donateInAProjectWhithLessThan1000(){ 	
		User aUser = mock(User.class);
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
		Calendar date = Calendar.getInstance();
		Donation donation= new Donation(aUser, project, date, 500);
		aUser.addDonation(donation);
				
		assertEquals(donation.getUser(), aUser);		
		assertEquals(donation.getProject(), project);
		assertEquals(donation.getDateDonation(), date);
		assertEquals(donation.getAmount(), 500);
	}
}