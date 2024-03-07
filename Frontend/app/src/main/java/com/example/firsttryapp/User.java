package com.example.firsttryapp;

public class User {

    private String id, name, lastName, username, password, schedule, friends;

    public User(String id, String name, String lastName, String username, String password, String schedule, String friends) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.schedule = schedule;
        this.friends = friends;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchedule() { return schedule; }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getFriends() { return friends; }

    public void setFriends(String friends) {
        this.friends = friends;
    }

}
