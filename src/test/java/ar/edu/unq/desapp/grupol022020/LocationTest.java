package ar.edu.unq.desapp.grupol022020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupol022020.model.Location;

public class LocationTest {
	@Test
	public void createNewLocation(){
		Location location = new Location();
		location.setHasConnectivity(false);
		location.setName("Mendoza");
		location.setPopulation(300000);
		location.setProvince("Mendoza");
		location.setId(11);
		location.setProjectAssociated(false);
		
		assertEquals(location.getHasConnectivity(), false);
		assertEquals(location.getName(), "Mendoza");
		assertEquals(location.getPopulation(), 300000);
		assertEquals(location.getProvince(), "Mendoza");	
		assertEquals(location.getId(), 11);	
		assertEquals(location.getProjectAssociated(), false);	
	}
	
	@Test
	public void createNewLocation2(){
		Location location = new Location("Quilmes", "Buenos Aires", 300000, true);
	
		assertEquals(location.getName(), "Quilmes");
		assertEquals(location.getProvince(), "Buenos Aires");	
		assertEquals(location.getPopulation(), 300000);
		assertEquals(location.getHasConnectivity(), true);
	}
}