package com.SUSocial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SUSocial.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>{

}
