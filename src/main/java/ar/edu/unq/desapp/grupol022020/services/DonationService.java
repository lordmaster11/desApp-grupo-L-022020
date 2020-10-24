package ar.edu.unq.desapp.grupol022020.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.UserException;
import ar.edu.unq.desapp.grupol022020.repositories.DonationRepository;

@Service
public class DonationService {
	@Autowired
	private DonationRepository repository;
	
	@Transactional
	public Donation save(Donation model) throws UserException, ProjetcException {
		Donation donation = model.getUser().donate(model.getAmount(), model.getProject(),model.getComment());
		return donation;
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

	public List<Donation> findByUserID(Integer id) {
		return this.repository.findIdByUser(id);
	}

}