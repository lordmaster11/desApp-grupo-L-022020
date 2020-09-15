package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Administrator;
import ar.edu.unq.desapp.grupoL022020.model.Donation;
import ar.edu.unq.desapp.grupoL022020.model.Project;
import ar.edu.unq.desapp.grupoL022020.model.ProjetcException;
import ar.edu.unq.desapp.grupoL022020.model.User;

public class AdministratorTest {
	@Test
	public void calculateTop10DonorThreeUsersTest() {
		User cesar = mock(User.class);
		when(cesar.totalDonation()).thenReturn(1000);
		User marcelo = mock(User.class);
		when(marcelo.totalDonation()).thenReturn(10);
		User juan = mock(User.class);
		when(juan.totalDonation()).thenReturn(500);
		
		Administrator myAdministrador = new Administrator();
		myAdministrador.addUser(cesar);
		myAdministrador.addUser(marcelo);
		myAdministrador.addUser(juan);
		
		assertEquals(myAdministrador.top10DonorUsers().size(),3);
		assertEquals(myAdministrador.top10DonorUsers().get(0), cesar);
		assertEquals(myAdministrador.top10DonorUsers().get(1), juan);
		assertEquals(myAdministrador.top10DonorUsers().get(2), marcelo);		
	}
	
	@Test
	public void calculateTop10Donor12UserTest() {
		User cesar = mock(User.class);
		User marcelo = mock(User.class);
		User juan = mock(User.class);
		User kevin = mock(User.class);
		User rocio = mock(User.class);
		User david = mock(User.class);
		User cristian = mock(User.class);
		User luciana = mock(User.class);
		User ramiro = mock(User.class);
		User elba = mock(User.class);
		User pablo = mock(User.class);
		User maria = mock(User.class);
				
		when(cesar.totalDonation()).thenReturn(1500);
		when(marcelo.totalDonation()).thenReturn(1400);
		when(juan.totalDonation()).thenReturn(1300);
		when(kevin.totalDonation()).thenReturn(1300);
		when(rocio.totalDonation()).thenReturn(1200);
		when(david.totalDonation()).thenReturn(1000);
		when(cristian.totalDonation()).thenReturn(900);
		when(luciana.totalDonation()).thenReturn(700);
		when(ramiro.totalDonation()).thenReturn(600);
		when(elba.totalDonation()).thenReturn(500);
		when(pablo.totalDonation()).thenReturn(0);
		when(maria.totalDonation()).thenReturn(3000);
		
		Administrator myAdministrador = new Administrator();
		myAdministrador.addUser(cesar);
		myAdministrador.addUser(marcelo);
		myAdministrador.addUser(juan);
		myAdministrador.addUser(kevin);
		myAdministrador.addUser(rocio);
		myAdministrador.addUser(david);
		myAdministrador.addUser(cristian);
		myAdministrador.addUser(luciana);
		myAdministrador.addUser(ramiro);
		myAdministrador.addUser(elba);
		myAdministrador.addUser(pablo);
		myAdministrador.addUser(maria);
		
		assertEquals(myAdministrador.top10DonorUsers().size(),10);
		assertEquals(myAdministrador.top10DonorUsers().get(0), maria);
		assertEquals(myAdministrador.top10DonorUsers().get(1), cesar);
		assertEquals(myAdministrador.top10DonorUsers().get(2), marcelo);	
		assertEquals(myAdministrador.top10DonorUsers().get(3), juan);
		assertEquals(myAdministrador.top10DonorUsers().get(4), kevin);
		assertEquals(myAdministrador.top10DonorUsers().get(5), rocio);	
		assertEquals(myAdministrador.top10DonorUsers().get(6), david);
		assertEquals(myAdministrador.top10DonorUsers().get(7), cristian);
		assertEquals(myAdministrador.top10DonorUsers().get(8), luciana);	
		assertEquals(myAdministrador.top10DonorUsers().get(9), ramiro);
	}
	
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
		when(cristian.myTop10Donation()).thenReturn(donations1);
		User luciana = mock(User.class);
		when(luciana.myTop10Donation()).thenReturn(donations2);
		User ramiro = mock(User.class);
		when(ramiro.myTop10Donation()).thenReturn(donations3);
	
		Administrator myAdministrador = new Administrator();
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
		when(cristian.myTop10Donation()).thenReturn(donations1);
		User luciana = mock(User.class);
		when(luciana.myTop10Donation()).thenReturn(donations2);

	
		Administrator myAdministrador = new Administrator();
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

		Administrator myAdministrador = new Administrator();
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
		
		Administrator myAdministrador = new Administrator();
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
}
