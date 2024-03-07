package com.SUSocial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SUSocial.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{

}
