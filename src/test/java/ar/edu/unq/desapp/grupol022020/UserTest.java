package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserAdmin;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.model.UserException;

public class UserTest {
	@Test
	public void newUser(){ 		
		User aUser = new UserDonor ();
		
		aUser.setName("Juan");
		aUser.setMail("juan@gmail.com");
		aUser.setPassword("1234");
		aUser.setNick("Master");
		
		assertEquals(aUser.getName(), "Juan");		
		assertEquals(aUser.getMail(), "juan@gmail.com");
		assertEquals(aUser.getPassword(), "1234");
		assertEquals(aUser.getNick(), "Master");
	}
	
	@Test
	public void settersUser(){ 
		User aUser = new UserAdmin ();

		aUser.setName("Juan Carlos");
		aUser.setMail("juanca@gmail.com");
		aUser.setPassword("12345");
		aUser.setNick("Juanca");
		aUser.setId(11);
		aUser.setRole("ROLE_ADMIN");

		assertEquals(aUser.getName(), "Juan Carlos");		
		assertEquals(aUser.getMail(), "juanca@gmail.com");
		assertEquals(aUser.getPassword(), "12345");
		assertEquals(aUser.getNick(), "Juanca");
		assertEquals(aUser.getId(), 11);
		assertEquals(aUser.getRole(), "ROLE_ADMIN");
	}		

	@Test
	public void newUserDonorDonateInAProjectWhithLessThan1000() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Pepe", "pepe@gmail.com", "1234", "Argento");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(500, project, "First donation");
				
		assertEquals(aUser.getPoints(), 0);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 500);
	}

	@Test
	public void donateInAProjectWhithMoreThan1000() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Pepe", "pepe@gmail.com", "1234", "Argento");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(1500, project, "First donation");
				
		assertEquals(aUser.getPoints(), 1500);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 1500);
	}
		
	@Test
	public void donateInAProjectInATownWhithLessThan2000Populations() throws UserException, ProjetcException{ 
		UserDonor aUser = new UserDonor("Esteban", "esteban@gmail.com", "1234", "Kito");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(500, project, "First donation");
				
		assertEquals(aUser.getPoints(), 1000);		
		assertEquals(aUser.getDonations().size(), 1);
		assertEquals(aUser.totalDonation(), 500);
	}

	@Test
	public void donateInAProjectInATownWhithLessThan2000PopulationsAndMoreThan1000() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Francisco", "fran@gmail.com", "1234", "pancho");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(1000);
				
		aUser.donate(2000, project, "First donation");
		
		assertEquals(aUser.getPoints(), 6000);
		assertEquals(aUser.getDonations().size(), 1);		
		assertEquals(aUser.totalDonation(), 2000);
	}

	@Test
	public void donateInTwoAProjectsInTheSameMonth() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Fede", "fede@gmail.com", "12345", "facha");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(500);
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(3000, project, "First donation");
		aUser.donate(1500, project2, "Second donation");

		assertEquals(aUser.getPoints(), 11000);		
		assertEquals(aUser.getDonations().size(), 2);
		assertEquals(aUser.totalDonation(), 4500);
    }

	@Test
	public void donateInTwoAProjectsInDifferentMonth() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Fede", "fede@gmail.com", "1234", "facha");
		
		aUser.setLastDonationDate(new GregorianCalendar(2020, Calendar.APRIL,11));
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(1500, project2, "First donation");

		assertEquals(aUser.getPoints(), 1500);
		assertEquals(aUser.totalDonation(), 1500);
	}
	
	@Test
	public void donateInTwoAProjectsInDifferentYear() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Fede", "fede@gmail.com", "1234", "facha");
		
		aUser.setLastDonationDate(new GregorianCalendar(2019, Calendar.SEPTEMBER,11));
		
		Project project2 = mock(Project.class);
		Location location2 = mock(Location.class);
		when(project2.getLocationProject()).thenReturn(location2);
		when(location2.getPopulation()).thenReturn(30000);
								
		aUser.donate(1500, project2, "First donation");

		assertEquals(aUser.getPoints(), 1500);
		assertEquals(aUser.totalDonation(), 1500);
	}
	
	@Test
	public void userException() throws UserException{
		UserDonor aUser = new UserDonor("Felipe", "felipe@gmail.com", "12345", "Felix");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(100000);
		
		UserException exception = Assertions.assertThrows(UserException.class, () -> {
			aUser.donate(0, project, "First donation");
			});	
	    
		assertEquals("The amount of the donation cannot be less than or equal to 0", exception.getMessage());
	}
	
	@Test
	public void createProjectUserDonor() throws UserException, ProjetcException{ 	
		Location location = mock(Location.class);
		Calendar endOfProject= new GregorianCalendar(2020, Calendar.SEPTEMBER,1);
				
		UserDonor aUser = new UserDonor("Fede", "fede@gmail.com", "1234", "facha");
		
		UserException exception = Assertions.assertThrows(UserException.class, () -> {			
			aUser.createProject(location, "Lonzo", endOfProject);
			});	
		
		assertEquals("User cannot create projects", exception.getMessage());
	}
	
	@Test
	public void userAdminCannotDonate() throws UserException, ProjetcException{ 
		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(50000);
				
		UserAdmin aUser = new UserAdmin("Fede", "fede@gmail.com", "1234", "facha");
		
		UserException exception = Assertions.assertThrows(UserException.class, () -> {			
			aUser.donate(5000, project, "Donate to project");
			});	
		
		assertEquals("Admin user cannot donate", exception.getMessage());
	}
	
	@Test
	public void setFactorInProjetInAdmin() throws UserException, ProjetcException{ 	
		Project aProject = mock(Project.class);
				
		User aUser = new UserAdmin("Fede", "fede@gmail.com", "1234", "facha");	
		aUser.setFactorInProjet(aProject, 1000);
		
		verify(aProject).setFactor(1000);
	}
	
	@Test
	public void setPercentageRequiredForClosingInProjetInAdmin() throws UserException, ProjetcException{ 	
		Project aProject = mock(Project.class);
				
		User aUser = new UserAdmin("Fede", "fede@gmail.com", "1234", "facha");	
		aUser.setPercentageRequiredForClosingInProjet(aProject, 60);
		
		verify(aProject).setPercentageRequiredForClosing(60);
	}
	
	@Test
	public void setFactorInProjetInDonor() throws UserException, ProjetcException{ 	
		Project aProject = mock(Project.class);
				
		User aUser = new UserDonor("Fede", "fede@gmail.com", "1234", "facha");
		UserException exception = Assertions.assertThrows(UserException.class, () -> {			
			aUser.setFactorInProjet(aProject, 1000);
			});	
		
		assertEquals("User cannot update projects", exception.getMessage());		
	}
	
	@Test
	public void setPercentageRequiredForClosingInProjetInDonor() throws UserException, ProjetcException{ 	
		Project aProject = mock(Project.class);
		
		User aUser = new UserDonor("Fede", "fede@gmail.com", "1234", "facha");
		UserException exception = Assertions.assertThrows(UserException.class, () -> {			
			aUser.setPercentageRequiredForClosingInProjet(aProject, 60);
			});	
		
		assertEquals("User cannot update projects", exception.getMessage());		
	}
	
	@Test
	public void donateIn3Project() throws UserException, ProjetcException{ 	
		UserDonor aUser = new UserDonor("Pepe", "pepe@gmail.com", "1234", "Argento");

		Project project = mock(Project.class);
		Location location = mock(Location.class);
		when(project.getLocationProject()).thenReturn(location);
		when(location.getPopulation()).thenReturn(30000);
				
		aUser.donate(1500, project, "First donation");
		aUser.donate(10, project, "Second donation");
		aUser.donate(1, project, "Third donation");
				
		assertEquals(aUser.getPoints(), 2500);		
	}
}