package com.una.logitech.project.model.departments;

import com.una.logitech.project.model.ConnectionJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends ConnectionJDBC {

    private static DepartmentDAO instance;

    public DepartmentDAO() throws Exception {
        super();
    }

    public static DepartmentDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new DepartmentDAO();
        }
        return instance;
    }

    public List<Department> getDepartment() throws Exception {
        List<Department> departments = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM departments ORDER BY name";
        try {
            con = this.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer admin_id = rs.getInt("admin_id");
                String name = rs.getString("name");
                String manager = rs.getString("manager");
                String location = rs.getString("location");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                Department dep = new Department(id, admin_id, name, manager, location, telephone, email);
                departments.add(dep);
            }
            return departments;
        } finally {
            this.close(con, stm, rs);
        }
    }

    public void addDepartment(Department dep) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO departments(id,admin_id,name,manager,location,telephone,email) VALUES(?,?,?,?,?,?,?)";
        try {
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, dep.getId());
            stm.setInt(2, dep.getAdmin_id());
            stm.setString(3, dep.getName());
            stm.setString(4, dep.getManager());
            stm.setString(5, dep.getLocation());
            stm.setString(6, dep.getTelephone());
            stm.setString(7, dep.getEmail());
            stm.execute();
        } finally {
            this.close(con, stm);
        }
    }

    public void updateDepartment(Department dep) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        String sql = "UPDATE departments SET admin_id=?, name=?, manager=?, location=? telephone=?  email=? WHERE id=?";
        try {
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, dep.getId());
            stm.setInt(2, dep.getAdmin_id());
            stm.setString(3, dep.getName());
            stm.setString(4, dep.getManager());
            stm.setString(5, dep.getLocation());
            stm.setString(6, dep.getTelephone());
            stm.setString(7, dep.getEmail());
            stm.execute();
        } finally {
            this.close(con, stm);
        }
    }

    public Department getDepartment(int id) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Department dep = null;
        String sql="SELECT * FROM department WHERE id=?";
        try{
            con=this.getConnection();
            stm=con.prepareStatement(sql);
            stm.setInt(1, id);
            rs=stm.executeQuery();
            if (true) {
                dep=new Department();
                dep.setId(rs.getInt("id"));
                dep.setAdmin_id(rs.getInt("admin_id"));
                dep.setName(rs.getString("name"));
                dep.setManager(rs.getString("manager"));
                dep.setLocation(rs.getString("location"));
                dep.setTelephone(rs.getString("telephone"));
                dep.setEmail(rs.getString("email"));
            }else{
                this.close(con, stm, rs);
                throw new Exception("No se puede encontrar el departamento:"+id);
            }
            return dep;
        }finally{
            this.close(con, stm, rs);
        }
    }
}
