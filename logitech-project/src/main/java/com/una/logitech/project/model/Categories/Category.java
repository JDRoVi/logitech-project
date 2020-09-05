package com.una.logitech.project.model.Categories;
import java.io.Serializable;
import javax.inject.Named;

@Named
public class Category implements Serializable{
    private int id;
    private String code;
    private String name;
    private String description;
    private String block;

    public Category() {
    }

    public Category(int id, String code,String name, String description, String block) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.block = block;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    
    

    
}
