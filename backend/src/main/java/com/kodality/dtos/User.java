package com.kodality.dtos;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String distanceTraveld;
    private List<TravelLog> logs;

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistanceTraveld() {
        return distanceTraveld;
    }

    public void setDistanceTraveld(String distanceTraveld) {
        this.distanceTraveld = distanceTraveld;
    }

    public List<TravelLog> getLogs() {
        return logs;
    }

    public void setLogs(List<TravelLog> logs) {
        this.logs = logs;
    }
}
