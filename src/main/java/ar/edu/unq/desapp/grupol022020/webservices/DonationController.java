package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.aspects.LogExecutionTime;
import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.UserException;
import ar.edu.unq.desapp.grupol022020.services.DonationService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@EnableAutoConfiguration
public class DonationController {    
    @Autowired
    private DonationService donationService;
    
	@LogExecutionTime
    @GetMapping("/api/donations")
    public ResponseEntity<?> allDonations() {
        List<Donation> list = donationService.findAll();

        return ResponseEntity.ok().body(list);
    }
    
	@LogExecutionTime
    @GetMapping("/api/donations/{id}")
    public ResponseEntity<?> getDonationById(@PathVariable("id") Integer id) {
    	try {
    		Donation donation = donationService.findByID(id);

    		return ResponseEntity.ok().body(donation);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("Donation with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@LogExecutionTime
    @GetMapping("/api/donationsUser/{id}")
    public ResponseEntity<?> getDonationsByUserId(@PathVariable("id") Integer id) {
    	try {
    		List<Donation> list = donationService.findByUserID(id);

    	    return ResponseEntity.ok().body(list);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("User with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@LogExecutionTime
    @PostMapping("/api/donation")
    public ResponseEntity<Donation> createDonation(@Validated 
    		@RequestParam ("userId") Integer userId,
			@RequestParam ("projectId") Integer projectId,
			@RequestParam ("amount") Integer amount,
		    @RequestParam ("comment") String comment) throws UserException, ProjetcException {
		
		Donation newDonation = donationService.createDonation(userId, projectId, amount, comment);

		return ResponseEntity.ok().body(newDonation);
    }
}