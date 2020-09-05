
package com.una.logitech.project.model.Categories;
import com.una.logitech.project.model.ConnectionJDBC;
import java.sql.Connection;
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
}
