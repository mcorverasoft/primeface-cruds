/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

