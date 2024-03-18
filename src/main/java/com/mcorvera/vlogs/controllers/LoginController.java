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
import org.mindrot.jbcrypt.BCrypt;

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
        jakarta.servlet.http.HttpServletRequest req = getRequest();
        User user;
        try {
            user = repositoryUser.findByUserorEmail(codeOrEmail);
            if (user != null) {
                if (this.verifyPassword(password, user.getPassword())) {  
                    req.getSession().setAttribute("user", user);
                    return "/system/home.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MESSAGE", "Please,Check your credentials"));
                }

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MESSAGE", "Please,Check your credentials"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MESSAGE", "Please,Check your credentials"));

        }
        return null;
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String logOut() {
        getRequest().getSession().invalidate();
        return "login.xhtml";
    }
}
