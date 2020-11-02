package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.User;

public class ProjectTest {
	@Test
	public void newProject2() throws ProjetcException{ 	
		Calendar dateStart = new GregorianCalendar(2020, Calendar.SEPTEMBER,1); 
		Calendar dateEnd = new GregorianCalendar(2020, Calendar.SEPTEMBER,30); 
		
		Project project = new Project();
		List<Donation> donations =new ArrayList<>();

		project.setId(11);
		project.setFantasyName("Conectar Argentina");
		project.setNumberOfDonors(10);
		project.setIsOpen(true);
		project.setProjectStart(dateStart);
		project.setEndOfProject(dateEnd);
		project.setMoneyNeeded(100000);
		project.setDonations(donations);

		assertEquals(project.getId(), 11);
		assertEquals(project.getFantasyName(), "Conectar Argentina");
		assertEquals(project.getNumberOfDonors(), 10);
		assertEquals(project.getIsOpen(), true);
		assertEquals(project.getProjectStart(), dateStart);
		assertEquals(project.getEndOfProject(), dateEnd);
		assertEquals(project.getMoneyNeeded(), 100000);
		assertEquals(project.getDonations().size(), 0);		
		assertEquals(project.getLastDonation(), null);		
	}
	
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
									   .withPercentageRequiredForClosing(75)
									   .withProjectStart(Calendar.getInstance())
									   .build();
		
		assertEquals(myProject.calculateMoneyNeeded(), 3000);
	}
		
	@Test
	public void newProject() throws ProjetcException{ 	
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);
		Calendar dateStart = new GregorianCalendar(2020, Calendar.SEPTEMBER,1); 
		Calendar lastDonation = new GregorianCalendar(2020, Calendar.AUGUST,30);
		
		Project project = new Project.ProjectBuilder(location)
									 .withFactor(10)
									 .withFantasyName("Conectarme")
									 .withProjectStart(dateStart)
									 .withPercentageRequiredForClosing(75)
									 .build();		
		project.setLastDonation(lastDonation);
				
		assertEquals(project.getLocationProject(), location);
		assertEquals(project.getFactor(), 10);
		assertEquals(project.getFantasyName(), "Conectarme");
		assertEquals(project.getProjectStart(), dateStart);
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
	
	@Test
	public void donateInprojectWithMoneyNeededIs60000() throws ProjetcException {
		Calendar todayPlusAMonth = Calendar.getInstance();
		todayPlusAMonth.add(Calendar.MONTH, -2);
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(5999);
		myProject.receiveDonation(donation1);

		assertEquals(myProject.calculateMoneyNeeded(), 60000);
		assertEquals(myProject.getDonatedAmount(), 5999);
	}
	
	@Test
	public void donateInprojectWithMoneyNeededIs60000Exception() throws ProjetcException{
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(10000000);
		
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
				myProject.receiveDonation(donation1);
		  });	
		
		assertEquals("It is not possible to make a donation. The maximum amount possible is 60000", exception.getMessage());
	}
	
	@Test
	public void donateInprojectwithExpiredDate() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(5999);
		myProject.receiveDonation(donation1);
		Calendar todayPlusAMonth = Calendar.getInstance();
		todayPlusAMonth.add(Calendar.MONTH, 2);
		
		assertEquals(myProject.getEndOfProject().get(Calendar.YEAR),todayPlusAMonth.get(Calendar.YEAR));
		assertEquals(myProject.getEndOfProject().get(Calendar.MONTH),todayPlusAMonth.get(Calendar.MONTH));
	}

	@Test
	public void donateInprojectwithEndOfProjectIsNull() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .withEndOfProject(null)
									   .build();
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(100);

		assertEquals(myProject.getEndOfProject(),null);	
	}
	
	@Test
	public void donateInprojectwithEndOfProjectnotIsExireted() throws ProjetcException {
		Calendar dateEnd = Calendar.getInstance(); 

		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .withEndOfProject(dateEnd)
									   .build();
		
		project.addTimeIfMissing();
		Calendar endOfProject = dateEnd;
		endOfProject.add(Calendar.MONTH, 2);

		assertEquals(project.getEndOfProject().get(Calendar.YEAR),endOfProject.get(Calendar.YEAR));	
		assertEquals(project.getEndOfProject().get(Calendar.MONTH),endOfProject.get(Calendar.MONTH));	
	}
	
	@Test
	public void setLocationProject() throws ProjetcException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(3000);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		
		project.setLocationProject(location);

		assertEquals(project.getDonatedAmount(), 600000);	
		assertEquals(project.getLocationProject(), location);	
	}
	
	@Test
	public void donateInProjectClose() throws ProjetcException{
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		
		Donation donation = mock(Donation.class);
		project.setIsOpen(false);		
		
		ProjetcException exception = Assertions.assertThrows(ProjetcException.class, () -> {
				project.receiveDonation(donation);
		  });	
		
		assertEquals("Project is close", exception.getMessage());
	}
	
	@Test
	public void donateInprojectAndCloseProject() throws ProjetcException{
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		project.setMoneyNeeded(10000);
		project.setDonatedAmount(9000);
		
		Donation donation = mock(Donation.class);
		when(donation.getAmount()).thenReturn(1000);
		
		project.receiveDonation(donation);
		
		assertEquals(project.getIsOpen(), false);
	}
	
	@Test
	public void userNotIsNewDonor() throws ProjetcException{
		Location location = mock(Location.class);
		User user = mock(User.class);

		when(location.getPopulation()).thenReturn(300);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		
		
		Donation donation = mock(Donation.class);
		when(donation.getUser()).thenReturn(user);
		
		Donation donation2 = mock(Donation.class);
		when(donation2.getUser()).thenReturn(user);
		
		project.receiveDonation(donation);
		project.receiveDonation(donation2);
		
		assertEquals(project.getNumberOfDonors(), 1);
		assertEquals(project.isNewDonor(user), false);
	}
	
	@Test
	public void userIsNewDonor() throws ProjetcException{
		Location location = mock(Location.class);
		User user = mock(User.class);

		when(location.getPopulation()).thenReturn(300);

		Project project = new Project.ProjectBuilder(location)
									   .withFactor(200)
									   .build();
		
		Donation donation = mock(Donation.class);
				
		project.receiveDonation(donation);

		assertEquals(project.getNumberOfDonors(), 1);
		assertEquals(project.isNewDonor(user), true);
	}
}