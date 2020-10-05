package ar.edu.unq.desapp.grupol022020.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private UserService userService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DonationService donationService;

	
	@PostConstruct
	public void initialize() throws ProjetcException, UserException {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}

	private void fireInitialData() throws ProjetcException, UserException {	
		Calendar dateEnd = new GregorianCalendar(2020, Calendar.SEPTEMBER,30); 
		Location location = new Location("Quilmes", "Buenos Aires", 500000, true);
		Location location2 = new Location("Purmamarca", "Jujuy", 100000, false);
		Project project = new Project.ProjectBuilder(location).withFantasyName("Conectar").build();
		Project project2 = new Project.ProjectBuilder(location2).withFantasyName("Conectarse").build();
		User userDonor = new UserDonor("Marcelo", "jm@gmail.com", "1234", "Master");
		User userAdmin = new UserAdmin("Cesar", "cesar@gmail.com", "1234", "Cesare");

		
		userDonor.donate(1000, project, "Donacion de 1000 pesos");
		userDonor.donate(2000, project, "Donacion de 2000 pesos");
		userDonor.donate(3000, project2, "Donacion de 3000 pesos");
		userAdmin.createProject(location2, "Si se puede", dateEnd);

		userService.save(userAdmin);
		userService.save(userDonor);
		
		projectService.save(project);
		projectService.save(project2);
		
		locationService.save(location);
		locationService.save(location2);
		
	}
}