package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.User;

public class ProjectTest {
	@Test
	public void createProjectWithDefaultFactor() {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project(location);
		assertEquals(myProject.calculateMoneyNeeded(), 300000);
	}
	@Test
	public void createProjectWithFactor200() {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project(location);
		myProject.setFactor(200);
		assertEquals(myProject.calculateMoneyNeeded(), 60000);
	}
	@Test
	public void createProjectWCompleteWithFactor10() {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project(location);
		myProject.setFactor(10);
		myProject.setFantasyName("Project Chaco");
		myProject.setEndOfProject(Calendar.getInstance());
		myProject.setPercentageRequiredForClosing(75);
		myProject.setProjectStart(Calendar.getInstance());
		
		assertEquals(myProject.calculateMoneyNeeded(), 3000);
	}
	
	@Test
	public void newProject(){ 	
		User aUser = mock(User.class);
		Location location = mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project project = new Project(location);
		project.setFactor(10);
		project.setFantasyName("Conectarme");
		Calendar dateStar = new GregorianCalendar(2020, Calendar.SEPTEMBER,1); 
		project.setProjectStart(dateStar);
		Calendar dateEnd = new GregorianCalendar(2020, Calendar.SEPTEMBER,30); 
		project.setEndOfProject(dateEnd);
		project.setPercentageRequiredForClosing(75);
		
		project.addDonor(aUser);
				
		assertEquals(project.getDonors().contains(aUser), true);		
		assertEquals(project.getLocationProject(), location);
		assertEquals(project.getFactor(), 10);
		assertEquals(project.getFantasyName(), "Conectarme");
		assertEquals(project.getProjectStart(), dateStar);
		assertEquals(project.getEndOfProject(), dateEnd);
		assertEquals(project.getPercentageRequiredForClosing(), 75);
	}
}
