package com.SUSocial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Schedule {

	@Id private String id;
	
	private String schedule;
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public Schedule(String id, User scheduleOwner, String schedule) {
		super();
		this.id = id;
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", schedule=" + schedule + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	
}
