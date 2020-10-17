package ar.edu.unq.desapp.grupol022020.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository  repository;
	
	@Transactional
	public UserDonor save(UserDonor model) {
		return this.repository.save(model);
	}

	public UserDonor findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<UserDonor> findAll() {
		return this.repository.findAll();
	}
	
	public UserDonor findByMail(String mail) {
		return this.repository.findByMail(mail).get();
	}
	
	public void deleteById(Integer id) {
		this.repository.deleteById(id);;		
	}

	@Transactional
	public UserDonor update(Integer id, UserDonor newUser) {
		UserDonor user = this.repository.findById(id).get();
		user = newUser;
		user.setId(id);
		return this.repository.save(user);
	}
	
    public UserDonor login(String mail, String pass) throws Exception {
		UserDonor user = null;
    	try {
    		user = findByMail(mail);
    		
    	} catch (Exception e){
    		throw new Exception("Access denied: mail not exist");
    	}
    	if(!user.getPassword().equals(pass))
			throw new Exception("Access denied: incorrect password");
			
    	return user;
    }
}