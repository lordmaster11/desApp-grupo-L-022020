package ar.edu.unq.desapp.grupol022020.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.User;
import ar.edu.unq.desapp.grupol022020.model.UserAdmin;
import ar.edu.unq.desapp.grupol022020.model.UserDonor;
import ar.edu.unq.desapp.grupol022020.repositories.UserRepository;
import ar.edu.unq.desapp.grupol022020.webservices.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository  repository;
	@Autowired
	private DonationService donationService;
	
	@Transactional
	public User save(User model) {
		
		User user = null;
    	try {
    		user = findByMail(model.getMail());
    		
    	} catch (Exception e){}
    	if(user!= null) {
    		throw new ResourceNotFoundException("Access denied: User already exist");
    	}
    	if(model.getRole().equals("ROLE_ADMIN")) {
    		return this.repository.save((UserAdmin) model);
    	}else {	
    		return this.repository.save((UserDonor) model);
    	}		
	}

	public User findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public User findByMail(String mail) {
		return this.repository.findByMail(mail).get();
	}
	
	public void deleteById(Integer id) {
		this.repository.deleteById(id);		
	}

	@Transactional
	public User update(Integer id, String name, String password, String nick, String role) {
		User user = this.repository.findById(id).get();
		if(name != null) {
			user.setName(name);
		}
		if(password != null) {
			user.setPassword(password);
		}
		if(nick != null) {
			user.setNick(nick);
		}
		if(role != null) {
			user.setRole(role);
		}
		return this.repository.save(user);
	}
	
    public User login(String mail, String pass) {
    	User user = null;
    	try {
    		user = findByMail(mail);
    		
    	} catch (Exception e){
    		throw new ResourceNotFoundException("Access denied: User not exist");
    	}
    	if(!user.getPassword().equals(pass)) {
    		throw new ResourceNotFoundException("Access denied: Incorrect password");
    	}
	
    	return user;
    }

	public User register(String name, String mail, String password, String nick) {
		User newUser = new UserDonor(name, mail, password, nick);
				
		return save(newUser);
	}

	public List<String> findUserDonorProject(Integer id) {
		List<String> nickDonors = new ArrayList<String>();
		for(Donation donation : donationService.getDonationsProject(id)) {
			String nick = donation.getUser().getNick();
			if(!nickDonors.contains(nick)) {
				nickDonors.add(nick);
			}
		}
		return nickDonors;
	}
}