package com.una.logitech.project.model.products;

import com.una.logitech.project.model.ConnectionJDBC;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class productDAO extends ConnectionJDBC {
    private static productDAO instance;
    
    public productDAO() throws Exception{
        super();
    }
    
    public static productDAO getInstance() throws Exception{
        if(instance == null){
            instance = new productDAO();
        }
        return instance;
    }
    
    public List<product> getProducts() throws Exception{
         List<product> Products = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM products ORDER BY code";
        try {
            con = this.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                product prod = new product();
                prod.setId(rs.getInt("id"));
                prod.setCategory_id(rs.getInt("category_id"));
                prod.setAdmin_id(rs.getInt("admin_id"));
                prod.setCode(rs.getString("code"));
                prod.setName(rs.getString("name"));
                prod.setDescription(rs.getString("description"));
                prod.setStock(rs.getInt("stock"));
                prod.setMin_stock(rs.getInt("min_stock"));
                prod.setMax_stock(rs.getInt("max_stock"));
                Products.add(prod);
            }
            return Products;
        } finally {
            this.close(con, stm, rs);
        }
    }
    
    public void addProduct(product prd) throws Exception{
        Connection con = null;
        PreparedStatement stm=null;
        String sql ="INSERT INTO products(id,category_id,admin_id,code,name,description,stock,min_stock,max_stock,image,file_name) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1,prd.getId());
            stm.setInt(2,prd.getCategory_id());
            stm.setInt(3,prd.getAdmin_id());
            stm.setString(4, prd.getCode());
            stm.setString(5, prd.getName());
            stm.setString(6, prd.getDescription());
            stm.setInt(7, prd.getStock());
            stm.setInt(8,prd.getMin_stock());
            stm.setInt(9,prd.getMax_stock());
            if (prd.getImage() != null) {
             stm.setBinaryStream(10,new ByteArrayInputStream(prd.getImage()));
            }else{
                stm.setBinaryStream(10, null);
            }
            stm.setString(11, prd.getFilename());
            stm.execute();
            
        }finally{
            this.close(con, stm);
        }
    }
    
    public void updateProduct(product prd)throws Exception{
        Connection con = null;
        PreparedStatement stm= null;
        String sql ="UPDATE products SET category_id=?,admin_id=?,code=?,name=?,description=?,stock=?,min_stock=?,max_stock=?,image=?,file_name=? WHERE id=?";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1,prd.getCategory_id());
            stm.setInt(2,prd.getAdmin_id());
            stm.setString(3, prd.getCode());
            stm.setString(4, prd.getName());
            stm.setString(5, prd.getDescription());
            stm.setInt(6, prd.getStock());
            stm.setInt(7,prd.getMin_stock());
            stm.setInt(8,prd.getMax_stock());
            stm.setInt(9,prd.getId());
            if (prd.getImage() != null) {
             stm.setBinaryStream(10,new ByteArrayInputStream(prd.getImage()));
            }else{
                stm.setBinaryStream(10, null);
            }
            stm.setString(11, prd.getFilename());
            stm.execute();            
        }finally{
            this.close(con, stm);
        }
    }
    
    public product getProduct(int id)throws Exception{
        Connection con=null;
        InputStream is = null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        product prd=null;
        String sql="SELECT * FROM products WHERE id=?";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1,id);
            rs=stm.executeQuery();
            if(rs.next()){
                prd = new product();
                prd.setId(rs.getInt("id"));
                prd.setCategory_id(rs.getInt("category_id"));
                prd.setAdmin_id(rs.getInt("admin_id"));
                prd.setCode(rs.getString("code"));
                prd.setName(rs.getString("name"));
                prd.setDescription(rs.getString("description"));
                prd.setStock(rs.getInt("stock"));
                prd.setMax_stock(rs.getInt("max_stock"));
                prd.setMin_stock(rs.getInt("min_stock"));
                            
                
            }else{
                this.close(con, stm,rs);
                throw new Exception("No se puede encontrar el producto:"+id);
            }
            return prd;
        }finally{
            this.close(con, stm, rs);
        }
    }
    
     public void deleteProduct(int id) throws Exception{
         Connection con = null;
         PreparedStatement stm = null;
         String sql = "DELETE FROM products WHERE id=?";
         try{
             con = this.getConnection();
             stm = con.prepareStatement(sql);
             stm.setInt(1,id);
             stm.execute();
         }finally{
            this.close(con, stm);
        }
         
    }
    
}
