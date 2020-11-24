package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @GetMapping("/api/users")
    public ResponseEntity<?> allUsers() {
        List<User> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
    	try {
    		User user = userService.findByID(id);
    		return ResponseEntity.ok().body(user);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
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
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
	@PostMapping("/api/users/login")
	public ResponseEntity<User> login(@Valid @RequestParam ("mail") @NotBlank @Email String mail,
									  @RequestParam ("password") @NotBlank String password) {	
		
		User userLogin = userService.login(mail, password);

		return ResponseEntity.ok().body(userLogin);	
	}
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
	@PostMapping(path="/api/users/register")
	public ResponseEntity<User> register(@Valid @RequestParam ("name") @NotBlank String name,
										 @RequestParam ("mail") @NotBlank @Email String mail,
										 @RequestParam ("password") @NotBlank String password,
									  	 @RequestParam ("nick") @NotBlank String nick) {
		
		User userRegistrate = userService.register(name, mail, password, nick);
		
		return ResponseEntity.ok().body(userRegistrate);	
	}
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
	@PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUserById(@Valid @PathVariable("id") Integer id, 
			@RequestParam (value = "name", required=false) String name,
			@RequestParam (value = "password", required=false) String password,
			@RequestParam (value = "nick", required=false) String nick, 
			@RequestParam (value = "role", required=false) String role) {
		
		User userUpdate = userService.update(id,name,password,nick,role);

		return ResponseEntity.ok().body(userUpdate);	
	}
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @GetMapping("/api/donorsOfProject/{id}")
    public ResponseEntity<?> getDonorsOfProject(@PathVariable("id") Integer id) {
    	try {
    		List<String> list = userService.findUserDonorProject(id);

    	    return ResponseEntity.ok().body(list);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("Project with ID:"+id+" Not Found!");
    	}    	   
    }
}