/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.controllers;

import com.mcorvera.vlogs.ejb.UserFacade;
import com.mcorvera.vlogs.model.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

/**
 *
 * @author miltoncorvera
 */
@Named
@RequestScoped
@Data
public class LoginController {
 
    @EJB
    private UserFacade repositoryUser;
    private String codeOrEmail;
    private String password;
    
    
    public String login() {
        User user;
        
        try{
            user = repositoryUser.findByLogin(codeOrEmail, password);
            if (user != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                request.getSession().setAttribute("user", user);
                return "/system/home.xhtml";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MESSAGE", "Please,Check your credentials"));
        }catch(Exception e){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MESSAGE", "Please,Check your credentials"));

        }
        
        return null;
    }
    
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public String logOut(){
        getRequest().getSession().invalidate();
        return "login.xhtml";
    } 
}
