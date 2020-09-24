package com.una.logitech.project.model.products;

import com.una.logitech.project.model.ConnectionJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends ConnectionJDBC {
    private static ProductDAO instance;
    
    public ProductDAO() throws Exception{
        super();
    }
    
    public static ProductDAO getInstance() throws Exception{
        if(instance == null){
            instance = new ProductDAO();
        }
        return instance;
    }
    
    public List<Product> getProduct() throws Exception{
         List<Product> Products = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM products ORDER BY code";
        try {
            con = this.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int category_id = rs.getInt("category_id");
                int admin_id = rs.getInt("admin_id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int stock = rs.getInt("stock");
                int min_stock = rs.getInt("min_stock");
                int max_stock = rs.getInt("max_stock");
                Product prod = new Product(id,category_id,admin_id,code,name,description,stock,max_stock,min_stock);
                Products.add(prod);
            }
            return Products;
        } finally {
            this.close(con, stm, rs);
        }
    }
    
    
}
