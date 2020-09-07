
package com.una.logitech.project.model.Categories;
import com.una.logitech.project.model.ConnectionJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends ConnectionJDBC{
    private static CategoryDAO instance;
    
    public CategoryDAO()throws Exception{
        super();
    }
    
    public static CategoryDAO getInstance() throws Exception{
       if(instance==null){
           instance=new CategoryDAO();
       }
       return instance;
    }
    public List<Category> getCategories() throws Exception{
        List<Category> Categories = new ArrayList<>();
        Connection con = null;
        Statement stm=null;
        ResultSet rs=null;
        String sql="SELECT * FROM categories ORDER BY name";
        try{
            con=this.getConnection();
            stm=con.createStatement();
            rs=stm.executeQuery(sql);
            while(rs.next()){
                Integer id=rs.getInt("id");
                String code=rs.getString("code");
                String name=rs.getString("name");
                String descrip=rs.getString("description");
                String block=rs.getString("block");
                Category est=new Category(id,code,name,descrip,block);                
                Categories.add(est);
            }
            return Categories;
        }finally{
            this.close(con, stm, rs);
        }
    }
    
     public void addCategory(Category cat) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO categories(id,code,name,description,block;) VALUES(?,?,?,?,?)";
        try {
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1,cat.getId());
            stm.setString(2,cat.getCode());
            stm.setString(3,cat.getName());
            stm.setString(4,cat.getDescription());
            stm.setString(5,cat.getBlock());
            stm.execute();
        }finally{
            this.close(con, stm);
        }
     }
     
     public void updateCategory(Category cat)throws Exception{
         Connection con = null;
         PreparedStatement stm = null;
         String sql = "UPDATE categories SET id=?,code=?,name=?,description=?,block=? WHERE id=?";
         try{  
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1,cat.getId());
            stm.setString(2,cat.getCode());
            stm.setString(3,cat.getName());
            stm.setString(4,cat.getDescription());
            stm.setString(5,cat.getBlock());
            stm.execute();
         }finally{
             this.close(con,stm);
         }
     }
     
     public Category getCategory(int id) throws Exception{
          Connection con = null;
          PreparedStatement stm = null;
          ResultSet rs = null;
          Category cat=null;
          String sql="SELECT * FROM categories WHERE id=?";
          try{
              con=this.getConnection();
              stm=con.prepareStatement(sql);
              stm.setInt(1, id);
              rs=stm.executeQuery();
              if(rs.next()){
                  cat = new Category();
                  cat.setId(rs.getInt("id"));
                  cat.setCode(rs.getString("code"));
                  cat.setDescription(rs.getString("description"));
                  cat.setBlock(rs.getString("block"));
                  
              }else{
                  this.close(con,stm,rs);
                  throw new Exception("No se puede encontrar la Categoria:"+id);
              }
              return cat;
          }finally{
              this.close(con,stm,rs);
          }
     }
    
     public void deleteCategories(int id) throws Exception{
         Connection con = null;
         PreparedStatement stm = null;
         String sql = "DELETE FROM categories WHERE id=?";
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
