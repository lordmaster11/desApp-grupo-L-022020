package ar.edu.unq.desapp.grupol022020.services;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserAdmin;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.model.UserException;

@Service
@Transactional
public class InitServiceInMemory {
	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${spring.datasource.driverClassName:NONE}")
	private String className;
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private DonationService donationService;

	@PostConstruct
	public void initialize() throws ProjetcException, UserException, Exception {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}

	private void fireInitialData() throws ProjetcException, UserException, Exception {		
		Location location = new Location("Quilmes", "Buenos Aires", 500000, true);
		Location location2 = new Location("Purmamarca", "Jujuy", 100000, false);

	//	locationService.save(location);
	//	locationService.save(location2);
		
		Project project = new Project.ProjectBuilder(location).withFantasyName("Conectar").build();
		projectService.save(project);
				
		User user = new UserAdmin("Marcelo", "jm@gmail.com", "1234", "Master");
		userService.save(user);
		User user2 = new UserAdmin("Cesar", "cesar@gmail.com", "1234", "Cesare");
		userService.save(user2);

		Project project2 = new Project.ProjectBuilder(location2).withFantasyName("Viva Internet").build();
		User user3 = new UserDonor("Esteban", "esteban@gmail.com", "1234", "Kito");
		
		Donation donation = new Donation(user3, project2, Calendar.getInstance(), 2000, "First donation");
		donationService.save(donation);
	}
}