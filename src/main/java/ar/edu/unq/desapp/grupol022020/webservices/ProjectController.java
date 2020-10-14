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
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;

import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.services.ProjectService;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceBadRequestException;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@RestController
@EnableAutoConfiguration
@Validated
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/projects")
    public ResponseEntity<?> allLocations() {
        List<Project> list = projectService.findAll();
        return ResponseEntity.ok().body(list);
    } 
    
    @GetMapping("/api/projects/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
    	try {
    		Project project = projectService.findByID(id);
        
    		return ResponseEntity.ok().body(project);
        
    	} catch (NoSuchElementException e){
    		throw new ResourceNotFoundException("Project with ID:"+id+" Not Found!");
    	}    	   
    }
    
	@DeleteMapping(value="/api/projects/{id}")
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
}