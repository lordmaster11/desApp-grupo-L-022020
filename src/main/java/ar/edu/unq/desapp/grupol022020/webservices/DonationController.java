package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.services.DonationService;

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
    public ResponseEntity<?> getDonation(@PathVariable("id") Integer id) {
        Donation donation = donationService.findByID(id);
        return ResponseEntity.ok().body(donation);
    }
}