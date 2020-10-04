
package com.una.logitech.project.controller;

import com.una.logitech.project.model.administrators.Administrator;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginAdmController implements Serializable{
    private Administrator Adm;
    
    @PostConstruct
    public void init(){
        setAdm(new Administrator());
        
    }
    
    public String login(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("logged",this.getAdm().getId());
        System.out.println(this.getAdm().getUser());
        System.out.println(this.getAdm().getPassword());
        return "/users/add-user";
    }
    
    public String logout(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    /**
     * @return the Adm
     */
    public Administrator getAdm() {
        return Adm;
    }

    /**
     * @param Adm the Adm to set
     */
    public void setAdm(Administrator Adm) {
        this.Adm = Adm;
    }
    
    
}