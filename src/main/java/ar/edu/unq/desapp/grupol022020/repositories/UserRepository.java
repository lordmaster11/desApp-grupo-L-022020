package ar.edu.unq.desapp.grupol022020.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupol022020.model.User;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findById(Integer id);
	
	List<User> findAll();

	Optional<User> findByMail(String name);
}