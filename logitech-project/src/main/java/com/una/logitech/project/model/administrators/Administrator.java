package com.una.logitech.project.model.administrators;
import javax.inject.Named;

@Named
public class Administrator{
    private Integer id;
    private String user;
    private String email;
    private String password;
    private String name;
    private String surname;
    
    public Administrator(){
        
    }
    
    public Administrator(Integer id,String user, String email, String password, String name, String surname){
        this.id = id;
         this.user = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
       
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUser(String user){
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }
    
    public String getUser(){
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}