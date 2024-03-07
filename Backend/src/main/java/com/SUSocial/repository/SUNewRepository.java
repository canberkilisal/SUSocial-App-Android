package com.SUSocial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SUSocial.model.SUNew;

public interface SUNewRepository extends MongoRepository<SUNew, String>{

	List<SUNew> findByTitleContainingIgnoreCase(String title);
}
