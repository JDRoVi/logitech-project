
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
    private byte[] temImg;
    private String tempFilename="product.jpg";
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    public ProductController()throws Exception{
        products = new ArrayList<>();
        dao = productDAO.getInstance();
    }
    
    public List<product> getProducts(){
        return products;
    }
    public void newInstance(){
        this.setProduct(new product());
    }
    
    private void addErrorMessage(String msg){
        FacesMessage mensaje=new FacesMessage("Error:"+msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public String loadProduct(int id){
        logger.info("Cargando los datos para actualizar producto id:"+id);
        try{
            this.setProduct(dao.getProduct(id));
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
    
     public String addProduct(){
        logger.info("Guardando producto:"+this.getProduct().getId());
        try{
            this.pdr.setImage(temImg);
            this.pdr.setFilename(tempFilename);
            dao.addProduct(this.getProduct());
        }catch(Exception ex){
            logger.log(Level.SEVERE,"Error agregando producto",ex);
            addErrorMessage(ex.getMessage());
            return null;
            
        }
        return "/products/list-products?faces-redirect=true";
    }
     
    public void upload(FileUploadEvent evento){
        try{
            this.temImg=evento.getFile().getContent();
            this.tempFilename=evento.getFile().getFileName();
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

    public void setProduct(product product) {
        this.pdr = product;
    }
    
    public product getProduct(){
        return pdr;
    }
    
    public String getTempFilename(){
        return this.tempFilename;
    }
    
}
