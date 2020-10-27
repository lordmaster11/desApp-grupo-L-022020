package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.services.LocationService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@EnableAutoConfiguration
public class LocationController {
    @Autowired
    private LocationService locationService;
    
    @GetMapping("/api/locations")
    public ResponseEntity<?> allLocations() {
        List<Location> list = locationService.findAll();
        return ResponseEntity.ok().body(list);
    } 
    
    @GetMapping("/api/locationsPossible")
    public ResponseEntity<?> locationsPossibleProject() {
        List<Location> list = locationService.findPossibleProject();
        return ResponseEntity.ok().body(list);
    } 
    
    @GetMapping("/api/locations/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Integer id) {
    	try {
    		Location location = locationService.findByID(id);
    		return ResponseEntity.ok().body(location);
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Location with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@PostMapping("/api/location")
    public ResponseEntity<Location> createLocation(@Validated @RequestBody Location location) {
    		Location newlocation = locationService.save(location);
        
    		return ResponseEntity.ok().body(newlocation);	
    }
	
	@DeleteMapping(value="/api/location/{id}")
    public ResponseEntity<?> deleteLocationById(@PathVariable("id") Integer id) {
    	try {
    		Location location = locationService.findByID(id);
        
    		locationService.deleteById(location.getId());
    		return ResponseEntity.ok().body("Location deleted with success!");	
        
    	}catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Location with ID:"+id+" Not Found!");
    	}catch (DataIntegrityViolationException e){
    		throw new ResourceBadRequestException("Location with ID:"+id+", cannot be deleted "
    											  + "because it has a current project");
    	}
    }
}