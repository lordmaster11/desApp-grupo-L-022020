package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Donation;
import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.ProjetException;
import ar.edu.unq.desapp.grupoL022020.model.User;

public class ProjectTest {
	@Test
	public void createProjectWithDefaultFactor() throws ProjetException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location).build();
		assertEquals(myProject.calculateMoneyNeeded(), 300000);
	}
	@Test
	public void createProjectWithFactor200() throws ProjetException {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project.ProjectBuilder(location)
										.withFactor(200)
										.build();
		assertEquals(myProject.calculateMoneyNeeded(), 60000);
	}
	@Test
	public void createProjectWCompleteWithFactor10() throws ProjetException {
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
	public void newProject() throws ProjetException{ 	
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
	public void addNewDonation() throws ProjetException{ 	
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
	public void addNewProjectwithPercentageRequiredForClosingIs10() throws ProjetException{
		Location location = mock(Location.class);
							
		Assertions.assertThrows(ProjetException.class, () -> {
			new Project.ProjectBuilder(location)
						.withPercentageRequiredForClosing(10)
						.build();
		  });	
	}
	@Test
	public void addNewProjectwithfactoris2000000() throws ProjetException{
		Location location = mock(Location.class);
							
		Assertions.assertThrows(ProjetException.class, () -> {
			new Project.ProjectBuilder(location)
						.withFactor(2000000)
						.build();
		  });	
	}
}
