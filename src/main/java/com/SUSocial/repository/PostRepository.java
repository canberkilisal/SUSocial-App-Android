package com.SUSocial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SUSocial.model.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
