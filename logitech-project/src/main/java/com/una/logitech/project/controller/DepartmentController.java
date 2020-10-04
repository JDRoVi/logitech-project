
package com.una.logitech.project.controller;

import com.una.logitech.project.model.departments.Department;
import com.una.logitech.project.model.departments.DepartmentDAO;
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
public class DepartmentController implements Serializable {
    private List<Department> departments;
    private DepartmentDAO dao;
    private Department depa;
    private final Logger logger=Logger.getLogger(this.getClass().getName());
    
    public DepartmentController()throws Exception{
        departments = new ArrayList<>();
        dao = DepartmentDAO.getInstance();
    }
    
     public List<Department> getDepartments(){
        return departments;
    }
    
    public void newInstance(){
        this.setDepartment(new Department());
    }
    public void loadDepartments(){
        logger.info("Cargando los departamentos");
        departments.clear();
        try{
            departments = dao.getDepartments();
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Error al cargar los datos",ex);
            addErrorMessage(ex.getMessage());
        }
    }
    
    public String loadDepartment(int id){
        logger.info("Cargando los datos para actualizar el departamento id:"+id);
        try{
            ExternalContext cont=FacesContext.getCurrentInstance().getExternalContext();
            Map<String,Object> mapa=cont.getSessionMap();
            mapa.put("actDepartment", this.depa);
        }catch(Exception ex){
            logger.log(Level.WARNING,"Error cargando el departamento id:"+id,ex);
            this.addErrorMessage("Problemas al cargar el registro desde la DB");
            return null;
        }        
        return "/departments/update-department";
    }
    
    public String addDepartment(){
        logger.info("Guardando el departamento:"+this.depa.getId());
        try{
            dao.addDepartment(getDepartment());
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error agregando el departamento",ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/departments/list-departments?faces-redirect=true";
    }
    
     public String updateDepartment(Department dep){
        logger.info("Guardando nuevos datos departmento id: "+dep.getId());
        try{
            dao.updateDepartment(dep);
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error actualizando departamento",ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/departments/list-departments?faces-redirect=true";
    }
    
    
    public String deleteDepartment(int id){
        logger.info("Eliminando curso id:"+id);
        try{
            dao.deleteDepartment(id);
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Error al eliminar el Departamento:"+id,ex);
            this.addErrorMessage("Error al eliminar el registro");
            return null;
        }        
        return "/departments/list-departments?faces-redirect=true";
    }

    private void setDepartment(Department department) {
        this.depa = department;
    }

    public void addErrorMessage(String message) {
         FacesMessage mensaje=new FacesMessage("Error:"+message);
         FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public Department getDepartment() {
        return depa;
    }
}
