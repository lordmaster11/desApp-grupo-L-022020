package ar.edu.unq.desapp.grupol022020.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserException;
import ar.edu.unq.desapp.grupol022020.repositories.DonationRepository;

@Service
public class DonationService {
	@Autowired
	private DonationRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	
	@Transactional
	public Donation save(Donation donation) {
		return this.repository.save(donation);
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
	
	public Donation createDonation(Integer userId, Integer projectId, Integer amount, String comment) throws UserException, ProjetcException {
		User user = this.userService.findByID(userId);
		Project project = this.projectService.findByID(projectId);
		
		Donation newDonation = user.donate(amount, project, comment);
		
		return save(newDonation);
	}

	public List<Donation> getTop10() {
		return this.repository.top10();
	}
}