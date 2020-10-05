package com.una.logitech.project.controller;

import com.una.logitech.project.model.administrators.Administrator;
import com.una.logitech.project.model.administrators.AdministratorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AdminController implements Serializable {
    
    private List<Administrator> admins;
    private AdministratorDAO dao;
    private Administrator admin;
    private final Logger logger= Logger.getLogger(this.getClass().getName());

    public AdminController() throws Exception {
        admins = new ArrayList<>();
        dao = AdministratorDAO.getInstance();
        this.loadAdmins();
    }
    
    public List<Administrator> getAdmins(){
        return this.admins;
    }
    
    public void newInstances(){
        this.admin = new Administrator();
    }
    
    private void addErrorMessage(String message) {
        FacesMessage mensaje=new FacesMessage("Error:"+message);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public void loadAdmins(){
        logger.info("Cargando administradores");
        admins.clear();
        try{
            admins = dao.getAdministrators();
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error al cargar los datos,ex");
            addErrorMessage(ex.getMessage());
        }
    }
    
    public String loadAdmin(int id){
        logger.info ("Cargando los datos del administrador id:"+id);
        try{
            this.admin = dao.getAdministrators(id);
            ExternalContext cont= FacesContext.getCurrentInstance().getExternalContext();
            Map<String,Object> mapa = cont.getSessionMap();
            mapa.put("actAdmi",this.admin);
        }catch(Exception ex){
            logger.log(Level.WARNING,"Error cargando el admin id:"+id,ex);
            this.addErrorMessage("Problemas al cargar el registro desde la DB");
            return null;
        }
        return "/users/update-user";
    }
    
    public String addAdmin(){
        logger.info("Guardando admin id: "+admin.getId());
        try{
            dao.addAdministrator(admin);
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error agregando al administrador",ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/users/list-users";
    }
    
    public String updateAdmin(Administrator adm){
        logger.info("Guardando nuevos datos admin id: "+this.admin.getId());
        try{
            dao.updateAdministrators(adm);
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error actualizando administrador",ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/users/list-users";
    }
    
    public String deleteAdmin(int id){
        logger.info("Eliminando admin id:"+id);
        try{
            dao.deleteAdministrators(id);
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Error al eliminar el admin:"+id,ex);
            this.addErrorMessage("Error al eliminar el registro");
            return null;
        }        
        return "/users/list-users?faces-redirect=true";
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
}