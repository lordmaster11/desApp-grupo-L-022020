package ar.edu.unq.desapp.grupol022020.services;

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

	@PostConstruct
	public void initialize() throws ProjetcException {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}

	private void fireInitialData() throws ProjetcException {		
		Location location = new Location("Quilmes", "Buenos Aires", 500000, true);
		Location location2 = new Location("Purmamarca", "Jujuy", 100000, false);

		locationService.save(location);
		locationService.save(location2);
		
		Project project = new Project.ProjectBuilder(location).withFantasyName("Conectar").build();
//		projectService.save(project);
	}
}