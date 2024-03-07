package com.SUSocial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.SUSocial.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByUsername(String username);
	public List<User> findByUsernameContainsIgnoreCase(String username);
	public boolean existsByUsername(String username);
}
