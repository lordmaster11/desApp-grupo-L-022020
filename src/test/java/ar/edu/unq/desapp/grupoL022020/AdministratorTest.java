package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Administrator;
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
	public void calculateTop10DonorTwelvesersTest() {
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
	
}
