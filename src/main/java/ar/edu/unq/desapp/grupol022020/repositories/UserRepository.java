package ar.edu.unq.desapp.grupol022020.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupol022020.model.UserDonor;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<UserDonor, Integer> {
	Optional<UserDonor> findById(Integer id);
	
	List<UserDonor> findAll();

	Optional<UserDonor> findByMail(String name);
}