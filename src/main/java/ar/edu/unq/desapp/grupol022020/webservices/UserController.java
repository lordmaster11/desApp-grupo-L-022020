package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.services.UserService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
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
	
	@PostMapping("/api/users/login")
	public ResponseEntity<User> login(@RequestParam MultiValueMap<String, String> user) throws Exception {	
		User userLogin = userService.login(user.getFirst("mail"), user.getFirst("password"));
		
		return ResponseEntity.ok().body(userLogin);	
	}
	
	@PostMapping(path="/api/users/register")
	public @ResponseBody ResponseEntity<User> register(@Validated @RequestParam MultiValueMap user) throws Exception {
		User newUser = new UserDonor((String) user.getFirst("name"), 
									 (String) user.getFirst("mail"), 
									 (String) user.getFirst("password"), 
									 (String) user.getFirst("nick"));
		
		User userRegistrate = userService.save(newUser);
		return ResponseEntity.ok().body(userRegistrate);	
	}
	
	
	@PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Integer id, @Validated @RequestParam MultiValueMap user) {
    	//try {
    		User userUpdate = userService.update(id, (String) user.getFirst("name"),
													 (String) user.getFirst("password"), 
													 (String) user.getFirst("nick"));
        
    		return ResponseEntity.ok().body(userUpdate);	
        
    	//} catch (NoSuchElementException e){
    	//	throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}
    }
	
	/*
	@PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateLocationById(@PathVariable("id") Integer id, @Validated @RequestBody UserDonor user) {
    	try {
    		User userUpdate = userService.update(id, user);
        
    		return ResponseEntity.ok().body(userUpdate);	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}
    }

	@PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateLocationById(@PathVariable("id") Integer id, @Validated @RequestBody UserDonor user) {
    	try {
    		User userUpdate = userService.update(id, user);
        
    		return ResponseEntity.ok().body(userUpdate);	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}
    }
	
	
	@PostMapping(path="/api/register")
	public @ResponseBody ResponseEntity<UserDonor> register(@Validated @RequestBody UserDonor user) {
		UserDonor newUser = userService.save(user);
	   
		return ResponseEntity.ok().body(newUser);	
	}
   	try {
	Optional<User> userRepo = userService.findByName(name);
	
	User userFinal = userRepo.get();
	
	if(password.equals(userFinal.getPassword())) {
		return ResponseEntity.ok().body("User login success!");
	} else {
		return ResponseEntity.ok().body("User login deneid!");
	}
 	} catch (Exception e){
		throw new Exception("User with Name:"+name+" Not Found!");
 	}  
}	*/
	
	/*
	@PutMapping(path="/api/users/{id}", consumes = "application/json", produces= "application/json")
	@ResponseBody
    public ResponseEntity<?> updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
    	try {
    		userService.update(id, user);
        
    		return ResponseEntity.ok().body("User update success!");	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	} catch (DataIntegrityViolationException e){
    		throw new ResourceBadRequestException("User with ID:"+id+", cannot be update!");
    	}
    }*/
