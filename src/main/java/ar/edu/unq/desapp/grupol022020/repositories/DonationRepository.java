package ar.edu.unq.desapp.grupol022020.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupol022020.model.Donation;

@Configuration
@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {
	@Query("SELECT r FROM Donation r where r.user.id = :id") 
	List<Donation> findIdByUser(@Param("id") Integer id);
	
	Optional<Donation> findById(Integer id);
	
	List<Donation> findAll();
}