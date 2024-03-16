/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.ejb;

import com.mcorvera.vlogs.model.Blog;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author miltoncorvera
 */
@Stateless
@LocalBean
public class BlogFacade extends AbstractFacade<Blog> implements RepositoryFacade<Blog>{
    
    @PersistenceContext(unitName = "blogs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogFacade() {
        super(Blog.class);
    }
}
