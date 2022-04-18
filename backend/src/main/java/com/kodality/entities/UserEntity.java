package com.kodality.entities;

import java.util.List;

public class UserEntity {

    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String distanceTraveld;
    public List<TravelLogEntity> logs;

    public UserEntity() { }

    public UserEntity(Long id, String name, Integer age, String address, String distanceTraveld, List<TravelLogEntity> logs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.distanceTraveld = distanceTraveld;
        this.logs = logs;
    }

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

    public List<TravelLogEntity> getLogs() {
        return logs;
    }

    public void setLogs(List<TravelLogEntity> logs) {
        this.logs = logs;
    }
}
