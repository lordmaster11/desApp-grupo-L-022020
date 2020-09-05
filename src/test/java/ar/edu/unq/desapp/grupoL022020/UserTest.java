package ar.edu.unq.desapp.grupoL022020;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoL022020.model.User;

public class UserTest {
	User aUser = new User ("Juan", "juan@gmail.com", "1234");

	@Test
	public void getNombreTest(){ 	
		assertTrue(aUser.getName().equals("Juan"));	
	}
		
	@Test
	public void getMail(){ 	
		assertTrue(aUser.getMail().equals("juan@gmail.com"));
	}	
}
