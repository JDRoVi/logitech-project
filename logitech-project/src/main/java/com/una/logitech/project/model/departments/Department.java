package com.una.logitech.project.model.departments;

import java.io.Serializable;

public class Department implements Serializable{
    private Integer id;
    private Integer admin_id;
    private String name;
    private String manager;
    private String location;
    private String telephone;
    private String email;
    
    public Department(){
        
    }
    
    public Department(Integer id,Integer admin_id,String name,String manager,String location,String telephone,String email){
        this.id = id;
        this.admin_id = admin_id;
        this.name = name;
        this.manager = manager;
        this.location = location;
        this.telephone = telephone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
