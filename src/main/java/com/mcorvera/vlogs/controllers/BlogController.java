package com.mcorvera.vlogs.controllers;

import com.mcorvera.vlogs.ejb.BlogFacade;
import com.mcorvera.vlogs.model.Blog;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author miltoncorvera
 */
@Named
@RequestScoped
@Data
public class BlogController {
    
    @EJB
    private BlogFacade blogsRepository;
    private List<Blog> blogs = new ArrayList<Blog>();
    private Blog blog = new Blog();
    
    @PostConstruct
    public void loadRecords(){
        this.blogs = blogsRepository.findAll();
    }
    
    public String newRecord(){
        this.blog.reset();
        return "";
    }
    
    public void save(){
        try{
            blogsRepository.merge(blog);
            this.loadRecords();
            this.blog.reset();
            this.showMessage("Record saved");
        }catch(Exception e){
            this.showMessage("Error at trying to create record");
        }
    }
    
    public void remove(){
        try{
            blogsRepository.remove(blog);
            this.loadRecords();
            this.blog.reset();
            this.showMessage("Record deleted");
        }catch(Exception e){
            this.showMessage("Error at trying to delete record");
        }
    }
    
    public void showMessage(String msg){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NOTICE", msg));
    }
}
