package com.una.logitech.project.controller;

import com.una.logitech.project.model.Categories.Category;
import com.una.logitech.project.model.Categories.CategoryDAO;
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
public class CategoryController implements Serializable {

    private List<Category> categories;
    private CategoryDAO dao;
    private Category category;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CategoryController() throws Exception {
        categories = new ArrayList<>();
        dao = CategoryDAO.getInstance();
        this.loadCategories();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void newInstance() {
        this.category = new Category();
    }

    private void addErrorMessage(String msg) {
        FacesMessage mensaje = new FacesMessage("Error:" + msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public void loadCategories() {
        logger.info("Cargando las categorias");
        categories.clear();
        try {
            categories = dao.getCategories();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al cargar los datos", ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public String loadCategory(int id) {
        logger.info("Cargando los datos para actualizar la categoria ID:" + id);
        try {
            ExternalContext cont = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> mapa = cont.getSessionMap();
            mapa.put("actCategory", this.category);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error cargando la categoria ID:" + id, ex);
            this.addErrorMessage("Problemas al cargar el registro desde la DB");
            return null;
        }
        return "/categories/update-product";
    }

    public String addCategory() {
        logger.info("Guardando estudiante:" + this.category.getId());
        try {
            dao.addCategory(this.category);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error agregando la categoria", ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/categories/list-categories";
    }

    public String updateCategory(Category ctg) {
        logger.info("Guardando los cambios de la categoria ID:" + this.category.getId());
        try {
            dao.updateCategory(ctg);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error actualizando la categoria", ex);
            addErrorMessage(ex.getMessage());
            return null;
        }
        return "/categories/list-categories?faces-redirect=true";
    }

    public String deleteCategory(int id) {
        logger.info("Eliminando la categoria ID:" + id);
        try {
            dao.deleteCategories(id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al eliminar la categoria:" + id, ex);
            this.addErrorMessage("Error al eliminar el registro");
            return null;
        }
        return "/categories/list-categories?faces-redirect=true";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}