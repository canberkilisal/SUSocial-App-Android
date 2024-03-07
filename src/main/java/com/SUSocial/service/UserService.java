package com.SUSocial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SUSocial.model.User;
import com.SUSocial.repository.UserRepository;

@Service
public class UserService {

	@Autowired 
	private UserRepository userRepository;
	
	public User createUser(User user) {
			
		if (userRepository.existsByUsername(user.getUsername())) {
			
			return null;
		}
		
		else {
			
			userRepository.save(user);
			
			return user;
		}

	}
	
	public User saveUser(User user) {
		
		userRepository.save(user);
		
		return user;
		
	}
	
	public User findByUsername(String username) {
		
		if (userRepository.findByUsername(username) == null) {
			
			return null;
		}
		
		else {
			
			User user = userRepository.findByUsername(username);
			
			return user;
		}

	}
	
	public List<User> searchByUsername(String username) {
		
		return userRepository.findByUsernameContainsIgnoreCase(username);
	}
	
	public boolean authenticateUser(String username, String password) {
		
		User user = userRepository.findByUsername(username);
		
		return user != null && user.getPassword().equals(password);
	}
	
	public List<User> getAllUsers() {
			
		return userRepository.findAll();
	}
		
}
