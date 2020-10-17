package ar.edu.unq.desapp.grupol022020.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.repositories.DonationRepository;

@Service
public class DonationService {
	@Autowired
	private DonationRepository  repository;
	
	@Transactional
	public Donation save(Donation model) {
		return this.repository.save(model);
	}

	public Donation findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<Donation> findAll() {
		return this.repository.findAll();
	}

	public Donation update(Integer id, Donation data) {
		Donation donation = findByID(id);
		donation = data;
		donation.setId(id);
		
		return this.repository.save(donation);
	}
}