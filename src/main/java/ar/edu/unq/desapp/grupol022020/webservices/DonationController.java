package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.UserException;
import ar.edu.unq.desapp.grupol022020.services.DonationService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@RestController
@EnableAutoConfiguration
public class DonationController {    
    @Autowired
    private DonationService donationService;
 
    @GetMapping("/api/donations")
    public ResponseEntity<?> allUsers() {
        List<Donation> list = donationService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/api/donations/{id}")
    public ResponseEntity<?> getDonationById(@PathVariable("id") Integer id) {
    	try {
    		Donation donation = donationService.findByID(id);
        
    		return ResponseEntity.ok().body(donation);
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Donation with ID:"+id+" Not Found!");
    	}    	   
    }
    
    @PostMapping("/api/donation")
    public ResponseEntity<Donation> createDonation(@Validated @RequestBody Donation donation) throws UserException, ProjetcException {
    		Donation newDonation = donationService.save(donation);
        
    		return ResponseEntity.ok().body(newDonation);	
    }
	
	@PutMapping("/api/donation/{id}")
    public ResponseEntity<Donation> updateDonationById(@PathVariable("id") Integer id, @Validated @RequestBody Donation donation) {
    	try {
    		Donation donationUpdate = donationService.update(id, donation);
        
    		return ResponseEntity.ok().body(donationUpdate);	
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Donation with ID:"+id+" Not Found!");
    	}
    }
}