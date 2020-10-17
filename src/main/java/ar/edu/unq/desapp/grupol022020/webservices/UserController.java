package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
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
        List<UserDonor> list = userService.findAll();
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
	
	@PostMapping("/api/login")
	public ResponseEntity<UserDonor> login(@RequestBody UserDonor user) throws Exception {	
		UserDonor userLogin = userService.login(user.getMail(), user.getPassword());
		
		return ResponseEntity.ok().body(userLogin);	
	}
	
	@PostMapping(path="/api/register")
	public @ResponseBody ResponseEntity<UserDonor> register(@Validated @RequestBody UserDonor user) {
		UserDonor newUser = userService.save(user);
	   
		return ResponseEntity.ok().body(newUser);	
	}
	
	@PutMapping("/api/user/{id}")
    public ResponseEntity<UserDonor> updateLocationById(@PathVariable("id") Integer id, @Validated @RequestBody UserDonor user) {
    	try {
    		UserDonor userUpdate = userService.update(id, user);
        
    		return ResponseEntity.ok().body(userUpdate);	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}
    }
	
	/*
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
		String name = user.get("name");
		String password = user.get("password");

		
		if(name == "" && password == "" ){
			//model.addAttribute("error", "Ingrese Usuario y Contraseña"); 
			//return "login";
    		return ResponseEntity.ok().body("Ingrese Name y Password");	

		}else if(name == "" && password != "") {
			//model.addAttribute("error", "Ingrese Usuario");
			//return "login";
    		return ResponseEntity.ok().body("Ingrese Name");	

		}else if(name != "" && password == "") {
			//model.addAttribute("error","Ingrese Contraseña");
			//return "login";
    		return ResponseEntity.ok().body("Ingrese Password");	
		}*/

		/*
    //	try {
    		Optional<User> userRepo = userService.findByName(name);
    		
    		User userFinal = userRepo.get();
    		
    		if(password.equals(userFinal.getPassword())) {
    			return ResponseEntity.ok().body("User login success!");
    		} else {
    			return ResponseEntity.ok().body("User login deneid!");
    		}
   // 	} catch (Exception e){
    //		throw new Exception("User with Name:"+name+" Not Found!");
   // 	}  
	}*/
		
		/*
		try{
		//	User user = this.mongo.login(user.getName(), user.getPassword());
			Optional<User> userR = Optional.ofNullable(userService.findByID(user.getId()));
			//model.addAttribute("user",user.getName());
			
			//String redirectUrl = request.getScheme() + ":/home";
			//request.setAttribute("user", user);
			//return "redirect:" + redirectUrl;
			
			
		
    		return ResponseEntity.ok().body("User login success!");	

			
		}catch(IndexOutOfBoundsException e){
			//model.addAttribute("error","El usuario no existe");
	    	//return "login";
    		return ResponseEntity.ok().body("El usuario no existe!");	

		}
	  }*/
	/*
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public String showRegistrationForm(Model model)  {
		model.addAttribute("msg", "Please Enter Your Login Details");
	    return "registrar";
	}
	
	@RequestMapping(value = "/registrar",method = RequestMethod.POST)
	public String registrar(HttpServletRequest request, Model model, @ModelAttribute("loginBean") Usuario usuario) throws Exception {		
		if(usuario.getUsuario() == "" && usuario.getPassword()== "" && usuario.getNombre() == "" && usuario.getApellido() == ""){
			model.addAttribute("error", "Ingrese los datos"); 
			return "registrar";
		}else if(usuario.getUsuario() == "" && usuario.getPassword() != "" && usuario.getNombre() != "" && usuario.getApellido() != "") {
			model.addAttribute("error", "Ingrese Usuario");
			return "registrar";
		}else if(usuario.getUsuario() != "" && usuario.getPassword() == "" && usuario.getNombre() != "" && usuario.getApellido() != "") {
			model.addAttribute("error","Ingrese Contraseña");
			return "registrar";
		}else if(usuario.getUsuario() != "" && usuario.getPassword() != "" && usuario.getNombre() == "" && usuario.getApellido() != "") {
			model.addAttribute("error","Ingrese Nombre");
			return "registrar";
		}else if(usuario.getUsuario() != "" && usuario.getPassword() != "" && usuario.getNombre() != "" && usuario.getApellido() == "") {
			model.addAttribute("error","Ingrese Apellido");
			return "registrar";
		}else if(usuario.getUsuario() != "" && usuario.getPassword() == "" && usuario.getNombre() == "" && usuario.getApellido() == "") {
			model.addAttribute("error", "Ingrese todos los datos");
			return "registrar";
		}	

		try{
			Usuario user = this.mongo.registrar(usuario);
			model.addAttribute("user",user.getUsuario());
			String redirectUrl = request.getScheme() + ":/home";
			
		    return "redirect:" + redirectUrl;
			
		}catch(Exception e){
			model.addAttribute("error","El usuario ya existe");
	    	return "registrar";
		}	
	  }*/
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
}