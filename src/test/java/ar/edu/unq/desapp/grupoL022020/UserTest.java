package ar.edu.unq.desapp.grupoL022020;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.desapp.grupoL022020.model.User;

public class UserTest {
	User aUser;

	@Before
	public void setUp() throws Exception {
		aUser = new User ("Juan", "juan@gmail.com", "1234");
	}

	@Test
	public void getNombreTest(){ 	
		assertTrue(aUser.getName().equals("Juan"));	
	}
		
	@Test
	public void getMail(){ 	
		assertTrue(aUser.getMail().equals("juan@gmail.com"));
	}	
}
