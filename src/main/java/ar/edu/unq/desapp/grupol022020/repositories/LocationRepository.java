package ar.edu.unq.desapp.grupol022020.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Location;

@Configuration
@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
	Optional<Location> findById(Integer id);
	
	List<Location> findAll();
	
	@Query("SELECT r FROM Location r where r.projectAssociated = false") 
	List<Location> findByhasntProject();
}