package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Donation;
import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.User;
import ar.edu.unq.desapp.grupoL022020.model.UserException;

public class UserTest {
	@Test
	public void newUser(){ 
		User aUser = new User.UserBuilder("Juan", "juan@gmail.com", "1234", "Master").build();
		
		assertEquals(aUser.getName(), "Juan");		
		assertEquals(aUser.getMail(), "juan@gmail.com");
		assertEquals(aUser.getPassword(), "1234");
		assertEquals(aUser.getNick(), "Master");
		assertEquals(aUser.getLastDonationDate(), null);		
	}
	
	@Test
	public void settersUser(){ 
		User aUser = new User.UserBuilder ("Juan", "juan@gmail.com", "1234", "Master")
								.withName("Juan Carlos")
								.withMail("juanca@gmail.com")
								.withPassword("12345")
								.withNick("Juanca")
								.withPoints(10000)
								.build();

		assertEquals(aUser.getName(), "Juan Carlos");		
		assertEquals(aUser.getMail(), "juanca@gmail.com");
		assertEquals(aUser.getPassword(), "12345");
		assertEquals(aUser.getNick(), "Juanca");
		assertEquals(aUser.getPoints(), 10000);
	}
	
	@Test
	public void donateInAProjectWhithLessThan1000() throws UserException{ 	
		User aUser = new User.UserBuilder("Pepe", "pepe@gmail.com", "1234", "Argento").build();

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
	public void donateInAProjectWhithMoreThan1000() throws UserException{ 	
		User aUser = new User.UserBuilder("Pepe", "pepe@gmail.com", "1234", "Argento").build();

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
	public void donateInAProjectInATownWhithLessThan2000Populations() throws UserException{ 	
		User aUser = new User.UserBuilder("Esteban", "esteban@gmail.com", "1234", "Kito").build();

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
	public void donateInAProjectInATownWhithLessThan2000PopulationsAndMoreThan1000() throws UserException{ 	
		User aUser = new User.UserBuilder("Francisco", "fran@gmail.com", "1234", "pancho").build();

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
	public void donateInTwoAProjectsInTheSameMonth() throws UserException{ 	
		User aUser = new User.UserBuilder("Fede", "fede@gmail.com", "12345", "facha").build();

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(500);
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(3000, project);
		aUser.donate(1500, project2);

		assertEquals(aUser.getPoints(), 11000);		
		assertEquals(aUser.getDonations().size(), 2);
		assertEquals(aUser.totalDonation(), 4500);
    }
	
	@Test
	public void donateInTwoAProjectsIndifferentMonth() throws UserException{ 	
		User aUser = new User.UserBuilder("Fede", "fede@gmail.com", "1234", "facha")
								.withLastDonationDate(new GregorianCalendar
														(2020, Calendar.APRIL,11))
								.build();
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(1500, project2);

		assertEquals(aUser.getPoints(), 1500);
		assertEquals(aUser.totalDonation(), 1500);
	}
	
	@Test
	public void myTop10DonationWith12Donation(){ 
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(1);
		Donation donation2 = mock(Donation.class);
		when(donation2.getAmount()).thenReturn(10);
		Donation donation3 = mock(Donation.class);
		when(donation3.getAmount()).thenReturn(30);
		Donation donation4 = mock(Donation.class);
		when(donation4.getAmount()).thenReturn(40);
		Donation donation5 = mock(Donation.class);
		when(donation5.getAmount()).thenReturn(50);
		Donation donation6 = mock(Donation.class);
		when(donation6.getAmount()).thenReturn(60);
		Donation donation7 = mock(Donation.class);
		when(donation7.getAmount()).thenReturn(70);
		Donation donation8 = mock(Donation.class);
		when(donation8.getAmount()).thenReturn(80);
		Donation donation9 = mock(Donation.class);
		when(donation9.getAmount()).thenReturn(90);
		Donation donation10 = mock(Donation.class);
		when(donation10.getAmount()).thenReturn(100);
		Donation donation11 = mock(Donation.class);
		when(donation11.getAmount()).thenReturn(1000);
		Donation donation12 = mock(Donation.class);
		when(donation12.getAmount()).thenReturn(2);	
		
		List<Donation> donations = new ArrayList<Donation>();
		donations.add(donation1);
		donations.add(donation2);
		donations.add(donation3);
		donations.add(donation4);
		donations.add(donation5);
		donations.add(donation6);
		donations.add(donation7);
		donations.add(donation8);
		donations.add(donation9);
		donations.add(donation10);
		donations.add(donation11);
		donations.add(donation12);
		User aUser = new User.UserBuilder("Mariano", "mar23@gmail.com", "1234", "facha")
								.withDonations(donations).
								build();

		assertEquals(aUser.myTop10Donation().size(), 10);
		assertEquals(aUser.myTop10Donation().get(0), donation11);
		assertEquals(aUser.myTop10Donation().get(1), donation10);
		assertEquals(aUser.myTop10Donation().get(2), donation9);
		assertEquals(aUser.myTop10Donation().get(3), donation8);
		assertEquals(aUser.myTop10Donation().get(4), donation7);
		assertEquals(aUser.myTop10Donation().get(5), donation6);
		assertEquals(aUser.myTop10Donation().get(6), donation5);
		assertEquals(aUser.myTop10Donation().get(7), donation4);
		assertEquals(aUser.myTop10Donation().get(8), donation3);
		assertEquals(aUser.myTop10Donation().get(9), donation2);
	}
	
	@Test
	public void myTop10DonationWith3Donation(){ 
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(110);
		Donation donation2 = mock(Donation.class);
		when(donation2.getAmount()).thenReturn(10);
		Donation donation3 = mock(Donation.class);
		when(donation3.getAmount()).thenReturn(30);
		
		List<Donation> donations = new ArrayList<Donation>();
		donations.add(donation1);
		donations.add(donation2);
		donations.add(donation3);

		User aUser = new User.UserBuilder("Mariano", "mar23@gmail.com", "1234", "facha")
								.withDonations(donations).
								build();

		assertEquals(aUser.myTop10Donation().size(), 3);
		assertEquals(aUser.myTop10Donation().get(0), donation1);
		assertEquals(aUser.myTop10Donation().get(1), donation3);
		assertEquals(aUser.myTop10Donation().get(2), donation2);
	}
	
	@Test
	public void theUserIsNotAddedToTheDonorList() throws UserException{
		User aUser = new User.UserBuilder("Oscar", "oscar@gmail.com", "12345", "Oscar").build();

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(100000);
		
	    ArrayList<User> donors = new ArrayList<User>();    
	    donors.add(aUser);

		when(project.getDonors()).thenReturn(donors);
		
		aUser.donate(1000, project);
		aUser.donate(5000, project);

	    assertEquals(project.getDonors().size(), 1);
	}
	
	@Test
	public void userException() throws UserException{
		User aUser = new User.UserBuilder("Felipe", "felipe@gmail.com", "12345", "Felix").build();

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(100000);
		
		UserException exception = Assertions.assertThrows(UserException.class, () -> {
			aUser.donate(0, project);
		});	
	    
		assertEquals("The donation cannot be less than 0", exception.getMessage());
	}
}