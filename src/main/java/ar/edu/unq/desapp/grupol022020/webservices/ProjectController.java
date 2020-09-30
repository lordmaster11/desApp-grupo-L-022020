package ar.edu.unq.desapp.grupol022020.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.services.ProjectService;

@RestController
@EnableAutoConfiguration
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/projects")
    public ResponseEntity<?> allLocations() {
        List<Project> list = projectService.findAll();
        return ResponseEntity.ok().body(list);
    }
}