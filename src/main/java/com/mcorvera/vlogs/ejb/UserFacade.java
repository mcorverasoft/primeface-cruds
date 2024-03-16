/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.ejb;

/**
 *
 * @author miltoncorvera
 */
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.mcorvera.vlogs.model.User;
import jakarta.ejb.LocalBean;
import java.util.Optional;

@Stateless
@LocalBean
public class UserFacade extends AbstractFacade<User> implements RepositoryFacade<User>{

    @PersistenceContext(unitName = "blogs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByLogin(String codeOrEmail, String password){
        User user=null;
        Optional optUser = Optional.of( em.createNamedQuery("User.findByCodeAndPassword").setParameter("codeOrEmail", codeOrEmail).setParameter("password", password).getSingleResult());
        if(optUser.isPresent())
            user=(User)optUser.get();
       return user;
        
    }   
}
