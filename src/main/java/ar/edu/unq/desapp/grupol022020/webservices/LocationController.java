package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.services.LocationService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

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
    
    @GetMapping("/api/locations/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Integer id) {
    	try {
    		Location location = locationService.findByID(id);
        
    		return ResponseEntity.ok().body(location);
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Location with ID:"+id+" Not Found!");
    	}    	   
    }
}