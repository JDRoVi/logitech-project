package com.una.logitech.project.model.administrators;
import java.io.Serializable;
import javax.inject.Named;

@Named
public class Administrator implements Serializable{
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String surname;
    
    public Administrator(){
        
    }
    
    public Administrator(Integer id, String email, String password, String name, String surname){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public void setId(Integer id) {
        this.id = id;
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