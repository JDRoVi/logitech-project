package com.una.logitech.project.controller;
import com.una.logitech.project.model.ConnectionJDBC;
import com.una.logitech.project.model.administrators.Administrator;

import com.una.logitech.project.model.administrators.Administrator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginAdmController extends ConnectionJDBC implements Serializable{
    private Administrator Adm;
    
    public LoginAdmController()throws Exception{
        super();
    }
    @PostConstruct
    public void init(){
        setAdm(new Administrator());
    }
    
    public String login(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("logged",this.getAdm().getId());
        PreparedStatement stm;
        ResultSet rs;
        Connection con;
        
        String sql ="SELECT username, password FROM administrators WHERE username = ?";
        
        try{
            con = this.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, Adm.getUser());
            rs = stm.executeQuery();
            if (rs.next()) {
                if (Adm.getPassword().equals(rs.getString(2))) {
                    return "/users/add-user";
                }else{
                    return "/login?faces-redirect=true";
                }
            }
            return "/login?faces-redirect=true";
        }catch (SQLException ex){
            Logger.getLogger(LoginAdmController.class.getName()).log(Level.SEVERE, null, ex);
            return "/login?faces-redirect=true";
        }
    }
    
    public String logout(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public Administrator getAdm() {
        return Adm;
    }

    public void setAdm(Administrator Adm) {
        this.Adm = Adm;
    }
}