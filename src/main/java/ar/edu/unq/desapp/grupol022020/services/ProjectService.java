package ar.edu.unq.desapp.grupol022020.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository repository;
	@Autowired
	private LocationService locationService;
	@Autowired
	private DonationService donationService;
	@Autowired
	private SendEmailService emailService;
	
	@Transactional
	public Project save(Project model) {
		return this.repository.save(model);
	}

	public Project findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<Project> findAll() {
		return this.repository.findAll();
	}

	public void deleteById(Integer id) {
		this.repository.deleteById(id);		
	}
	
	@Transactional
	public Project update(Integer id, Project data) {
		Project project = findByID(id);
		project = data;
		project.setId(id);
		
		return this.repository.save(project);
	}

	public Project closeProject(Integer id) throws ProjetcException {
		Project project = findByID(id);
		project.closeProject();
		List<Donation> donaciones = donationService.getDonationsProject(id);
		List<String> emails = new ArrayList<String>();
		for (Donation donation: donaciones) {
			String email = donation.getUser().getMail();
			if(!emails.contains(email)) {
				emails.add(email);
			}
		}
		this.emailService.sendEmailsClose(emails, project.getFantasyName());
		return this.repository.save(project);
	}

	public Project createProject(Integer locationProjectId, Integer factor, Integer percentageRequiredForClosing, String fantasyName) throws ProjetcException {
		Location location = this.locationService.findByID(locationProjectId);
		
		Project newProject = new Project.ProjectBuilder(location)
				.withFactor(factor)
				.withPercentageRequiredForClosing(percentageRequiredForClosing)
				.withFantasyName(fantasyName)
				.build();
		
		return save(newProject);
	}

	public List<Project> getTopLastDonations() {
		return this.repository.topLastDonation();
	}	
}