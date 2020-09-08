package ar.edu.unq.desapp.grupoL022020;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.Location;

public class LocationTest {
	@Test
	public void createNewLocation(){
		Location location = new Location();
		location.setHasConnectivity(false);
		location.setName("Mendoza");
		location.setPopulation(300000);
		location.setProvince("Mendoza");
		
		assertEquals(location.getHasConnectivity(), false);
		assertEquals(location.getName(), "Mendoza");
		assertEquals(location.getPopulation(), 300000);
		assertEquals(location.getProvince(), "Mendoza");	
	}
}
