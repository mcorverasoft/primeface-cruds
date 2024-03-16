/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.ejb;

import com.mcorvera.vlogs.beans.BlogReaderDetail;
import com.mcorvera.vlogs.model.Blog;
import com.mcorvera.vlogs.model.BlogReader;
import com.mcorvera.vlogs.model.Reader;
import com.mcorvera.vlogs.model.User;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author miltoncorvera
 */
@Stateless
@LocalBean
public class BlogReaderFacade extends AbstractFacade<BlogReader> implements RepositoryFacade<BlogReader>{
    
    @PersistenceContext(unitName = "blogs")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogReaderFacade() {
        super(BlogReader.class);
    }
    
    public List<BlogReader> getBlogReaderDetails() {
        String sql = "SELECT br.id, br.name, br.r_id readerId, r.name as readerName, br.b_id as blogId, "+
                     "b.title, br.u_id as userId, u.code " +
                     "FROM blogs_readers br " +
                     "INNER JOIN blogs b ON br.b_id = b.id " +
                     "INNER JOIN readers r ON br.r_id = r.id " +
                     "INNER JOIN users u ON br.u_id = u.id";
        Query query = em.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();
    
        // Mapear cada registro a un objeto BlogReader
        List<BlogReader> blogReaders = result.stream().map(record -> {
            BlogReader blogReader = new BlogReader();
            blogReader.setId((Integer) record[0]);
            blogReader.setName((String) record[1]);
            blogReader.setReaderId((Integer) record[2]);
            blogReader.setReaderName((String) record[3]);
            blogReader.setBlogId((Integer) record[4]);
            blogReader.setTitle((String) record[5]);
            blogReader.setUserId((Integer) record[6]);
            blogReader.setCode((String) record[7]);
            return blogReader;
        }).collect(Collectors.toList());

        return blogReaders;
    }
    
    
}
