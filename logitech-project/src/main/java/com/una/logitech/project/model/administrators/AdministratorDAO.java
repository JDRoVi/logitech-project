package com.una.logitech_project.logitech_project.model.administrators;

import com.una.logitech_project.logitech_project.model.ConnectionJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO extends ConnectionJDBC {

    private static AdministratorDAO instance;

    public AdministratorDAO() throws Exception {
        super();
    }

    public static AdministratorDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new AdministratorDAO();
        }
        return instance;
    }

    public List<Administrator> getAdministrators() throws Exception {
        List<Administrator> administrators = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrators ORDER BY name";
        try {
            con = this.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Administrator adm = new Administrator(id, email, password, name, surname);
                administrators.add(adm);
            }
            return administrators;
        } finally {
            this.close(con, stm, rs);
        }
    }

    public void addAdministrator(Administrator adm) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO administrators(id,email,password,name;surname) VALUES(?,?,?,?,?)";
        try {
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, adm.getId());
            stm.setString(2, adm.getEmail());
            stm.setString(3, adm.getPassword());
            stm.setString(4, adm.getName());
            stm.setString(5, adm.getSurname());
            stm.execute();
        } finally {
            this.close(con, stm);
        }
    }

    public void updateAdministrators(Administrator adm) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String sql="UPDATE administrators SET email=?, password=?, name=?, surname=? WHERE id=?";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1, adm.getId());
            stm.setString(2, adm.getEmail());
            stm.setString(3, adm.getPassword());
            stm.setString(4, adm.getName());
            stm.setString(5, adm.getSurname());
            stm.execute();
        }finally{
            this.close(con, stm);
        }
        
    }
    public Administrator getAdministrators(int id)throws Exception{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs=null;
        Administrator adm=null;
        String sql="SELECT * FROM administrators WHERE id=?";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1, id);
            rs=stm.executeQuery();
            if(rs.next()){
                adm=new Administrator();
                adm.setId(rs.getInt("id"));
                adm.setName(rs.getString("email"));
                adm.setName(rs.getString("password"));
                adm.setName(rs.getString("name"));
                adm.setName(rs.getString("surname"));
            }else{
                this.close(con, stm, rs);
                throw new Exception("No se puede encontrar el estudiante:"+id);
            }
            return adm;
        }finally{
            this.close(con, stm, rs);
        }
        
    }
}