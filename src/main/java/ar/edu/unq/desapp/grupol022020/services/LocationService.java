package ar.edu.unq.desapp.grupol022020.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Location;
import ar.edu.unq.desapp.grupol022020.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository  repository;
	
	@Transactional
	public Location save(Location model) {
		return this.repository.save(model);
	}

	public Location findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<Location> findAll() {
		return this.repository.findAll();
	}
	
	public void deleteById(Integer id) {
		this.repository.deleteById(id);		
	}

	public Location update(Integer id, Location data) {
		Location location = findByID(id);
		location = data;
		location.setId(id);
		
		return this.repository.save(location);
	}	
}