package ar.edu.unq.desapp.grupol022020.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupol022020.model.Project;

@Configuration
@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	Optional<Project> findById(Integer id);
	
	List<Project> findAll();

	@Query("SELECT r FROM Project r WHERE r.lastDonation != null and rownum<=10 ORDER BY r.lastDonation") 
	List<Project> topLastDonation();	
}