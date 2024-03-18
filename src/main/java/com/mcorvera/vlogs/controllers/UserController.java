/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.controllers;

import com.mcorvera.vlogs.ejb.UserFacade;
import com.mcorvera.vlogs.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.faces.context.ExternalContext;

/**
 *
 * @author miltoncorvera
 */
@Named
@RequestScoped
@Data
public class UserController {
    @EJB
    private UserFacade userRepository;
    private List<User> users = new ArrayList<User>();
    private User user= new User();
    
    @PostConstruct
    public void loadRecords(){
            this.users=userRepository.findAll();
    }
    
    public String newRecord(){
        this.user.reset();
        return "";
    }
    
    public void save(){
        try{
            this.user.setIsActive(Boolean.TRUE);
            //hashing password:
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            this.user.setPassword(hashedPassword);
            userRepository.merge(user);
            this.loadRecords();
            this.user.reset();
            this.showMessage("Record saved");
        }catch(Exception e){
            this.showMessage("Error at trying to create record");
        }
    }
    
    public void remove(){
        try{
            userRepository.remove(user);
            this.loadRecords();
            this.user.reset();
            this.showMessage("Record deleted");
        }catch(Exception e){
            this.showMessage("Error at trying to delete record");
        }
    }
    
    public HttpServletRequest getRequest() {
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public String logOut(){
        getRequest().getSession().invalidate();
        return "/login.xhtml?faces-redirect=true";
    } 
    
    private void showMessage(String msg){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NOTICE", msg));
    }
}
