package ar.edu.unq.desapp.grupol022020.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.aspects.LogExecutionTime;
import ar.edu.unq.desapp.grupol022020.aspects.LogExecutionTimeAspectAnnotation;
import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.services.UserService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@EnableAutoConfiguration
public class UserController {    
    @Autowired
    private UserService userService;  
    
	private static Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspectAnnotation.class);

	@LogExecutionTime
    @GetMapping("/api/users")
    public ResponseEntity<?> allUsers() {
        List<User> list = userService.findAll();
		logger.info("/////// Inside allUsers() method");

        return ResponseEntity.ok().body(list);
    }
	
	@LogExecutionTime
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
    	try {
    		User user = userService.findByID(id);
    		logger.info("/////// Inside getUserById() method");
    		return ResponseEntity.ok().body(user);
        
    	} catch (NoSuchElementException e){
    		logger.warn("/////// This message is logged because WARN: User with ID:" +id+" Not Found!");

    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@LogExecutionTime
	@DeleteMapping(value="/api/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
    	try {
    		User user = userService.findByID(id);
        
    		userService.deleteById(user.getId());
    		logger.info("/////// Inside deleteUserById() method");

    		return ResponseEntity.ok().body("User deleted with success!");	
        
    	} catch (NoSuchElementException e){
    		logger.warn("/////// This message is logged because WARN: User with ID:" +id+" Not Found!");

    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	} catch (DataIntegrityViolationException e){
    		throw new ResourceBadRequestException("User with ID:"+id+", cannot be deleted because it has donations!");
    	}
    }
	
	@LogExecutionTime
	@PostMapping("/api/users/login")
	public ResponseEntity<User> login(@RequestParam ("mail") String mail,
									  @RequestParam ("password") String password) {	
		
		User userLogin = userService.login(mail, password);
		logger.info("/////// Inside login() method");

		return ResponseEntity.ok().body(userLogin);	
	}
	
	@LogExecutionTime
	@PostMapping(path="/api/users/register")
	public ResponseEntity<User> register(@RequestParam ("name") String name,
										 @RequestParam ("mail") String mail,
										 @RequestParam ("password") String password,
									  	 @RequestParam ("nick") String nick) {
		
		User userRegistrate = userService.register(name, mail, password, nick);
		logger.info("/////// Inside register() method");
		
		return ResponseEntity.ok().body(userRegistrate);	
	}
	
	@LogExecutionTime
	@PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id") Integer id, 
			@RequestParam (value = "name", required=false) String name,
			@RequestParam (value = "password", required=false) String password,
			@RequestParam (value = "nick", required=false) String nick, 
			@RequestParam (value = "role", required=false) String role) {
		
		User userUpdate = userService.update(id,name,password,nick,role);
		logger.info("/////// Inside updateUserById() method");

		return ResponseEntity.ok().body(userUpdate);	
	}
}