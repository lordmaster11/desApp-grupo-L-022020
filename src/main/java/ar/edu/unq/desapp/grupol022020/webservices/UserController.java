package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.services.UserService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@RestController
@EnableAutoConfiguration
public class UserController {    
    @Autowired
    private UserService userService;    
 
    @GetMapping("/api/users")
    public ResponseEntity<?> allUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
    	try {
    		User user = userService.findByID(id);
        
    		return ResponseEntity.ok().body(user);
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@DeleteMapping(value="/api/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
    	try {
    		User user = userService.findByID(id);
        
    		userService.deleteById(user.getId());
    		return ResponseEntity.ok().body("User deleted with success!");	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	} catch (DataIntegrityViolationException e){
    		throw new ResourceBadRequestException("User with ID:"+id+", cannot be deleted because it has donations!");
    	}
    }
}