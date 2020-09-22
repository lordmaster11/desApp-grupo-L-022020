package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.System;
import ar.edu.unq.desapp.grupol022020.model.User;

public class SystemTest {
	@Test
	public void calculateBest10DonationsOn12DonationsTest() {
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
		
		List<Donation> donations1 = new ArrayList<Donation>();
		donations1.add(donation1);
		donations1.add(donation2);
		donations1.add(donation3);
		donations1.add(donation4);
		List<Donation> donations2 = new ArrayList<Donation>();
		donations2.add(donation5);
		donations2.add(donation6);
		donations2.add(donation7);
		donations2.add(donation8);
		List<Donation> donations3 = new ArrayList<Donation>();
		donations3.add(donation9);
		donations3.add(donation10);
		donations3.add(donation11);
		donations3.add(donation12);
		
		User cristian = mock(User.class);
		when(cristian.getDonations()).thenReturn(donations1);
		User luciana = mock(User.class);
		when(luciana.getDonations()).thenReturn(donations2);
		User ramiro = mock(User.class);
		when(ramiro.getDonations()).thenReturn(donations3);
	
		System myAdministrador = new System();
		myAdministrador.addUser(cristian);
		myAdministrador.addUser(luciana);
		myAdministrador.addUser(ramiro);
		
		List<Donation> bestDonations = myAdministrador.best10Donations();
		
		assertEquals(bestDonations.size(),10);
		assertEquals(bestDonations.get(0), donation11);
		assertEquals(bestDonations.get(1), donation10);
		assertEquals(bestDonations.get(2), donation9);
		assertEquals(bestDonations.get(3), donation8);
		assertEquals(bestDonations.get(4), donation7);
		assertEquals(bestDonations.get(5), donation6);
		assertEquals(bestDonations.get(6), donation5);
		assertEquals(bestDonations.get(7), donation4);
		assertEquals(bestDonations.get(8), donation3);
		assertEquals(bestDonations.get(9), donation2);		
	}
	
	@Test
	public void calculateBest10DonationsOn2DonationsTest() {
		Donation donation1 = mock(Donation.class);
		when(donation1.getAmount()).thenReturn(1);
		Donation donation2 = mock(Donation.class);
		when(donation2.getAmount()).thenReturn(10);
		
		List<Donation> donations1 = new ArrayList<Donation>();
		donations1.add(donation1);
		donations1.add(donation2);
		List<Donation> donations2 = new ArrayList<Donation>();
		
		User cristian = mock(User.class);
		when(cristian.getDonations()).thenReturn(donations1);
		User luciana = mock(User.class);
		when(luciana.getDonations()).thenReturn(donations2);

		System myAdministrador = new System();
		myAdministrador.addUser(cristian);
		myAdministrador.addUser(luciana);
		
		List<Donation> bestDonations = myAdministrador.best10Donations();
		
		assertEquals(bestDonations.size(),2);
		assertEquals(bestDonations.get(0), donation2);
		assertEquals(bestDonations.get(1), donation1);	
	}
	
	@Test
	public void calculateDonationFreeLocationsForLongerTest3Projects() throws ProjetcException {	
		Project project1 = mock(Project.class);
		when(project1.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.JULY,1));
		Project project2 = mock(Project.class);
		when(project2.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.FEBRUARY,11));
		Project project3 = mock(Project.class);
		when(project3.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.MARCH,11));
		
		List<Project> projects = new ArrayList<Project>();
		projects.add(project1);
		projects.add(project2);
		projects.add(project3);

		System myAdministrador = new System();
		myAdministrador.setProjects(projects);
		
		List<Project> myProjects = myAdministrador.donationFreeLocationsForLonger();
		assertEquals(myProjects.size(),3);
		assertEquals(myProjects.get(0), project2);
		assertEquals(myProjects.get(1), project3);
		assertEquals(myProjects.get(2), project1);
	}
	
	@Test
	public void calculateDonationFreeLocationsForLongerTest12Projects() throws ProjetcException {	
		Project project1 = mock(Project.class);
		when(project1.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.JANUARY,1));
		Project project2 = mock(Project.class);
		when(project2.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.FEBRUARY,11));
		Project project3 = mock(Project.class);
		when(project3.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.MARCH,11));
		Project project4 = mock(Project.class);
		when(project4.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.APRIL,11));
		Project project5 = mock(Project.class);
		when(project5.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.MAY,11));
		Project project6 = mock(Project.class);
		when(project6.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.JUNE,11));
		Project project7 = mock(Project.class);
		when(project7.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.JULY,11));
		Project project8 = mock(Project.class);
		when(project8.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.AUGUST,11));
		Project project9 = mock(Project.class);
		when(project9.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.SEPTEMBER,11));
		Project project10 = mock(Project.class);
		when(project10.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.OCTOBER,11));
		Project project11 = mock(Project.class);
		when(project11.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.NOVEMBER,11));
		Project project12 = mock(Project.class);
		when(project12.getLastDonation()).thenReturn(new GregorianCalendar(2020, Calendar.NOVEMBER,11));
		
		List<Project> projects = new ArrayList<Project>();
		projects.add(project1);
		projects.add(project2);
		projects.add(project3);
		projects.add(project4);
		projects.add(project5);
		projects.add(project6);		
		projects.add(project7);
		projects.add(project8);
		projects.add(project9);		
		projects.add(project10);
		projects.add(project11);
		projects.add(project12);
		
		System myAdministrador = new System();
		myAdministrador.setProjects(projects);
		
		List<Project> myProjects = myAdministrador.donationFreeLocationsForLonger();
		assertEquals(myProjects.size(),10);
		assertEquals(myProjects.get(0), project1);
		assertEquals(myProjects.get(1), project2);
		assertEquals(myProjects.get(2), project3);
		assertEquals(myProjects.get(3), project4);
		assertEquals(myProjects.get(4), project5);
		assertEquals(myProjects.get(5), project6);
		assertEquals(myProjects.get(6), project7);		
		assertEquals(myProjects.get(7), project8);
		assertEquals(myProjects.get(8), project9);
		assertEquals(myProjects.get(9), project10);
	}
	
	@Test
	public void createNewProject() throws ProjetcException {	
		Location location = mock(Location.class);
		Calendar endOfProject = new GregorianCalendar(2020, Calendar.SEPTEMBER,1); 
		
		System myAdministrador = new System();
		Project myProject = myAdministrador.createNewProject(location, "Efola", endOfProject);
		
		assertTrue(myAdministrador.getProjects().contains(myProject));
	}
	
	@Test
	public void createUserDonor() {	
		System myAdministrador = new System();
		User myUser = myAdministrador.createUserDonor("Juan", "juan@gmail.com", "1234", "Master");
		
		assertTrue(myAdministrador.getUser().contains(myUser));
	}
	
	@Test
	public void createUserAdmin() {	
		System myAdministrador = new System();
		User myUser = myAdministrador.createUserAdmin("Juan", "juan@gmail.com", "1234", "Master");
		
		assertTrue(myAdministrador.getUser().contains(myUser));
	}
}