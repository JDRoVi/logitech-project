
package com.una.logitech.project.controller;

import com.una.logitech.project.model.products.product;
import com.una.logitech.project.model.products.productDAO;
import com.una.logitech.project.resources.Util;
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
import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class ProductController implements Serializable {
    private List<product> products;
    private productDAO dao;
    private product pdr;

    public product getPdr() {
        return pdr;
    }

    public void setPdr(product pdr) {
        this.pdr = pdr;
    }
    private byte[] temImg;
    private String tempFilename="";
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    public ProductController()throws Exception{
        products = new ArrayList<>();
        dao = productDAO.getInstance();
        this.loadProducts();
    }
    
    public List<product> getProducts(){
        return products;
    }
    public void newInstance(){
        this.pdr = new product();
    }
    
    private void addErrorMessage(String msg){
        FacesMessage mensaje=new FacesMessage("Error:"+msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public String loadProduct(int id){
        logger.info("Cargando los datos para actualizar producto id:"+id);
        try{
            ExternalContext cont=FacesContext.getCurrentInstance().getExternalContext();
            Map<String,Object> mapa=cont.getSessionMap();
            mapa.put("actProduct", pdr);
        }catch(Exception ex){
            logger.log(Level.WARNING,"Error cargando el product id:"+id,ex);
            this.addErrorMessage("Problemas al cargar el registro desde la DB");
            return null;
        }        
        return "/products/update-product";
    }
    
    public void loadProducts(){
        logger.info("Cargando productos...");
        products.clear();
        try{
            products = dao.getProducts();
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Error al cargar los datos",ex);
            addErrorMessage(ex.getMessage());
        }
    }
    
     public String addProduct(){
        logger.info("Guardando producto:"+this.pdr.getId());
        try{
            this.pdr.setImage(temImg);
            this.pdr.setFilename(tempFilename);
            dao.addProduct(this.pdr);
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error agregando producto",ex);
            addErrorMessage(ex.getMessage());
            return null;
            
        }
        return "/products/list-products?faces-redirect=true";
    }
     
    public void upload(FileUploadEvent event){
        try{
            this.temImg=event.getFile().getContent();
            this.tempFilename=event.getFile().getFileName();
            Util.SaveImgTemporary(temImg, tempFilename);
        }catch(Exception ex){
            this.addErrorMessage(ex.getMessage());
            logger.log(Level.SEVERE,"Error cargando imagen",ex);
        }
        
    }
      
    public String deleteProduct(int id){
        logger.info("Eliminando products id:"+id);
        try{
            dao.deleteProduct(id);
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Error al eliminar producto:"+id,ex);
            this.addErrorMessage("Error al eliminar el registro");
            return null;
        }        
        return "/products/list-products?faces-redirect=true";
    }

    
    
    public String getTempFilename(){
        return this.tempFilename;
    }
    
}
