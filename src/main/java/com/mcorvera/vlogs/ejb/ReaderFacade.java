package com.mcorvera.vlogs.ejb;

import com.mcorvera.vlogs.model.Reader;
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
public class ReaderFacade extends AbstractFacade<Reader> implements RepositoryFacade<Reader>{
    
    @PersistenceContext(unitName = "blogs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReaderFacade() {
        super(Reader.class);
    }
    
}
