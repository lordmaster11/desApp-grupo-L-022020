package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Location;
import ar.edu.unq.desapp.grupoL022020.model.Project;

public class ProjectTest {

	@Test
	public void createProjectWithDefaultFactor() {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project(location);
		assertEquals(1000,myProject.calculateMoneyNeeded(), 300000);
	}
	@Test
	public void createProjectWithFactor200() {
		Location location=mock(Location.class);
		when(location.getPopulation()).thenReturn(300);

		Project myProject = new Project(location);
		myProject.setFactor(200);
		assertEquals(1000,myProject.calculateMoneyNeeded(), 60000);
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
		
		assertEquals(1000,myProject.calculateMoneyNeeded(), 3000);
	}
}
