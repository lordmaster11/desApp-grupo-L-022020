package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import ar.edu.unq.desapp.grupol022020.aspects.LogExecutionTime;
import ar.edu.unq.desapp.grupol022020.aspects.LogExecutionTimeAspectAnnotation;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.ProjetcException;
import ar.edu.unq.desapp.grupol022020.services.ProjectService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@EnableAutoConfiguration
@Validated
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @GetMapping("/api/projects")
    public ResponseEntity<?> allProjects() {
        List<Project> list = projectService.findAll();
		
        return ResponseEntity.ok().body(list);
    } 
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @GetMapping("/api/project/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
    	try {
    		Project project = projectService.findByID(id);

    		return ResponseEntity.ok().body(project);
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("Project with ID:"+id+" Not Found!");
    	}    	   
    }
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
	@PutMapping(path = "/api/closeProject/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("id") Integer id) throws ProjetcException {
    	try {
			Project projectUpdate = projectService.closeProject(id);

    		return ResponseEntity.ok().body(projectUpdate);	
    		
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("Project with ID:"+id+" Not Found!");
    	}
    }

	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
	@DeleteMapping(value="/api/project/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable("id") Integer id) {
    	try {
    		Project project = projectService.findByID(id);        
    		projectService.deleteById(project.getId());

    		return ResponseEntity.ok().body("Project deleted with success!");	
        
    	} catch (NoSuchElementException e){

    		throw new ResourceNotFoundException("Project with ID:"+id+" Not Found!");
    	} catch (DataIntegrityViolationException e){
    		throw new ResourceBadRequestException("Project with ID:"+id+", cannot be deleted because it has donations!");
    	}
    }
	
	@LogExecutionTime
	@LogExecutionTimeAspectAnnotation
    @PostMapping("/api/newProject")
    public ResponseEntity<Project> createProject(@Validated 
    		@RequestParam ("locationProjectId") Integer locationProjectId, 
    		@RequestParam ("factor") Integer factor,
			@RequestParam ("percentageRequiredForClosing") Integer percentageRequiredForClosing,
    		@RequestParam ("fantasyName") String fantasyName) throws ProjetcException{
   
		Project newProject = projectService.createProject(locationProjectId, factor, percentageRequiredForClosing, fantasyName);

		return ResponseEntity.ok().body(projectService.save(newProject));
    }
}