package com.mcorvera.vlogs.ejb;

/**
 *
 * @author miltoncorvera
 */
import java.util.List;

public interface RepositoryFacade<T> {
    void create(T entity);
    void merge(T entity);
    void edit(T entity);
    void remove(T entity);
    T find(Object id);
    List<T> findAll();
    List<T> findRange(int[] range);
    int count();
}

