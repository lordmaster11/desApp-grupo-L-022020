package ar.edu.unq.desapp.grupol022020.services;

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
	private UserService userService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DonationService donationService ;
	
	
	@PostConstruct
	public void initialize() throws ProjetcException, UserException, Exception {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}

	private void fireInitialData() throws ProjetcException, UserException, Exception {	
		Location location = new Location("Quilmes", "Buenos Aires", 500000, false);
		Location location2 = new Location("Purmamarca", "Jujuy", 100000, false);
		Location location3 = new Location("Ezpeleta City", "Buenos Aires", 300000, false);
		Location location4 = new Location("Cafayate", "Salta", 20000, false);
		Location location5 = new Location("Necochea", "Buenos Aires", 100000, false);
		Location location6 = new Location("La Lucila", "Buenos Aires", 50000, false);
		Location location7 = new Location("Bariloche", "Río Negro", 111000, false);

		Project project = new Project.ProjectBuilder(location).withFantasyName("Conectar").build();
		Project project2 = new Project.ProjectBuilder(location2).withFantasyName("Conectarse").build();
		Project project3 = new Project.ProjectBuilder(location3).withFantasyName("Internet para todos").build();
		Project project4 = new Project.ProjectBuilder(location4).withFantasyName("Internet").build();
		Project project5 = new Project.ProjectBuilder(location5).withFantasyName("Internet une").build();
		Project project6 = new Project.ProjectBuilder(location6).withFantasyName("Viva Internet").build();
		Project project7 = new Project.ProjectBuilder(location7).withFantasyName("Internet por fin").build();

		User userAdmin = new UserAdmin("Cesar", "cesar@gmail.com", "1234", "Cesare");
		User userDonor = new UserDonor("Marcelo", "jm@gmail.com", "1234", "Master");
		User userDonor2 = new UserDonor("Esteban", "ban@gmail.com", "1234", "Kito");
		User userDonor3 = new UserDonor("Ana", "ana@gmail.com", "1234", "Anita");
		User userDonor4 = new UserDonor("Lisandro", "lopez@gmail.com", "1234", "Licha");

		Donation donation = new Donation(userDonor, project, 1000, "Mi gran donacion");
		Donation donation2 = new Donation(userDonor, project, 5000, "Mi segunda gran donacion");
		Donation donation3 = new Donation(userDonor, project3, 2000, "La tercera es la vencida");
		Donation donation4 = new Donation(userDonor, project6, 3000, "Gracias por la magia!!!");

		Donation donation5 = new Donation(userDonor2, project2, 500, "Mi donacion");
		Donation donation6 = new Donation(userDonor2, project3, 10000, "Mi segunda donacion");
		
		Donation donation7 = new Donation(userDonor3, project4, 1000, "Donación");
		Donation donation8 = new Donation(userDonor3, project, 6000, "Unión, paz y armonía");

		Donation donation9 = new Donation(userDonor4, project5, 3000, "Primera");
		Donation donation10 = new Donation(userDonor4, project3, 3000, "Segunda");
		Donation donation11 = new Donation(userDonor4, project6, 3000, "Tercera");
		Donation donation12 = new Donation(userDonor4, project, 3000, "Cuarta");

		donationService.save(donation);
		donationService.save(donation2);
		donationService.save(donation3);
		donationService.save(donation4);
		donationService.save(donation5);
		donationService.save(donation6);
		donationService.save(donation7);
		donationService.save(donation8);
		donationService.save(donation9);
		donationService.save(donation10);
		donationService.save(donation11);
		donationService.save(donation12);

		userService.save(userAdmin);
		userService.save(userDonor);
//		userService.save(userDonor2);
//		userService.save(userDonor3);
//		userService.save(userDonor4);

		projectService.save(project);
		projectService.save(project2);
		projectService.save(project3);
		projectService.save(project4);
		projectService.save(project5);
		projectService.save(project6);
		projectService.save(project7);

		locationService.save(location);
		locationService.save(location2);
		locationService.save(location3);	
		locationService.save(location4);	
		locationService.save(location5);
		locationService.save(location6);	
		locationService.save(location7);	
	}
}