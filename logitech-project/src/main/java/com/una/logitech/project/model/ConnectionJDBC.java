package com.una.logitech.project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String strConnection = "jdbc:mysql://localhost/unadb?user=root&password=";

    public ConnectionJDBC() throws Exception {
        Class.forName(driver);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(strConnection);
    }
    
    protected void close(Connection con, Statement stm){
        this.close(con, stm, null);
    }

    protected void close(Connection con, Statement stm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}