package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.User;

public class ProjectTest {
	@Test
	public void createProjectWithDefaultFactor() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location).build();
		assertEquals(myProject.calculateMoneyNeeded(), 300000);
	}
	
	@Test
	public void createProjectWithFactor200() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		assertEquals(myProject.calculateMoneyNeeded(), 60000);
	}
	
	@Test
	public void createProjectWCompleteWithFactor10() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(10)
									   .withFantasyName("Project Chaco")
									   .withEndOfProject(Calendar.getInstance())
									   .withPercentageRequiredForClosing(75)
									   .withProjectStart(Calendar.getInstance())
									   .build();
		
		assertEquals(myProject.calculateMoneyNeeded(), 3000);
	}
		
	@Test
	public void newProject() throws ProjetcException{ 	
		User aUser = mock(User.class);
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);
		Calendar dateStart = new GregorianCalendar(2020, Calendar.SEPTEMBER,1); 
		Calendar dateEnd = new GregorianCalendar(2020, Calendar.SEPTEMBER,30); 
		
		Project project = new Project.ProjectBuilder(location)
									 .withFactor(10)
									 .withFantasyName("Conectarme")
									 .withProjectStart(dateStart)
									 .withEndOfProject(dateEnd)
									 .withPercentageRequiredForClosing(75)
									 .build();		
		project.addDonor(aUser);
				
		assertTrue(project.getDonors().contains(aUser));
		assertEquals(project.getLocationProject(), location);
		assertEquals(project.getFactor(), 10);
		assertEquals(project.getFantasyName(), "Conectarme");
		assertEquals(project.getProjectStart(), dateStart);
		assertEquals(project.getEndOfProject(), dateEnd);
		assertEquals(project.getPercentageRequiredForClosing(), 75);
		assertEquals(project.getDonations().size(), 0);		
	}
	
	@Test
	public void addNewDonation() throws ProjetcException{ 	
		Calendar dateDonation= new GregorianCalendar(2020, Calendar.SEPTEMBER,1);
		Location location = mock(Location.class);
		Donation aDonation = mock(Donation.class);
		when(aDonation.getDateDonation()).thenReturn(dateDonation);		

		Project project = new Project.ProjectBuilder(location).build();								
		project.addDonotion(aDonation);
				
		assertTrue(project.getDonations().contains(aDonation));		
		assertEquals(project.getLastDonation(), dateDonation);		
	}
	@Test
	public void setFactorPermitted() throws ProjetcException{ 	
		Location location = mock(Location.class);
		Project project = new Project.ProjectBuilder(location).build();								
		project.setFactor(1000);
				
		assertEquals(project.getFactor(), 1000);		
	}
	@Test
	public void setPercentageRequiredForClosingPermitted() throws ProjetcException{ 
		Location location = mock(Location.class);
		Project project = new Project.ProjectBuilder(location).build();								
		project.setPercentageRequiredForClosing(60);
				
		assertEquals(project.getPercentageRequiredForClosing(), 60);		
	}
	
	@Test
	public void projetcExceptionWhenPercentageRequiredForClosingHiglerA100() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .withPercentageRequiredForClosing(200)
					   .build();
		  });	
		assertEquals("The percentage required to close the project must be between 50 and 100 percent", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenPercentageRequiredForClosingLessA50() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .withPercentageRequiredForClosing(10)
					   .build();
		  });	
		assertEquals("The percentage required to close the project must be between 50 and 100 percent", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenTheFactorLessA0() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .withFactor(-10)
					   .build();
		  });	
		assertEquals("The project factor must be between 0 and 100000", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenTheFactorHiglerA100000() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .withFactor(2000000)
					   .build();
		  });	
		assertEquals("The project factor must be between 0 and 100000", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenPercentageRequiredForClosingHiglerA100InSetter() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .build()
					   .setPercentageRequiredForClosing(200);
		  });	
		assertEquals("The percentage required to close the project must be between 50 and 100 percent", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenPercentageRequiredForClosingLessA50InSetter() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .build()
					   .setPercentageRequiredForClosing(10);
		  });	
		assertEquals("The percentage required to close the project must be between 50 and 100 percent", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenTheFactorLessA0inSetter() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .build()
					   .setFactor(-10);
		  });	
		assertEquals("The project factor must be between 0 and 100000", exception.getMessage());
	}
	
	@Test
	public void projetcExceptionWhenTheFactorHiglerA100000inSetter() throws ProjetcException{
		Location location = mock(Location.class);
							
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
			new Project.ProjectBuilder(location)
					   .build()
					   .setFactor(2000000);
		  });	
		
		assertEquals("The project factor must be between 0 and 100000", exception.getMessage());
	}
}