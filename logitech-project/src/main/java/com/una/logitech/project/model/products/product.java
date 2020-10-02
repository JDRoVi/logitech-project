package com.una.logitech.project.model.products;

public class Product{
    private int id;
    private int category_id;
    private int admin_id;
    private String  code;
    private String  name;
    private String  description;
    private int stock;
    private int max_stock;
    private int min_stock;
    
    public Product(){
        
    }

    public Product(int id, int category_id, int admin_id, String code, String name, String description, int stock, int max_stock, int min_stock) {
        this.id = id;
        this.category_id = category_id;
        this.admin_id = admin_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.max_stock = max_stock;
        this.min_stock = min_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMax_stock() {
        return max_stock;
    }

    public void setMax_stock(int max_stock) {
        this.max_stock = max_stock;
    }

    public int getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(int min_stock) {
        this.min_stock = min_stock;
    }
    
    
}
