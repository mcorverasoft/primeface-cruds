/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.controllers;

import com.mcorvera.vlogs.beans.BlogReaderDetail;
import com.mcorvera.vlogs.ejb.BlogFacade;
import com.mcorvera.vlogs.ejb.BlogReaderFacade;
import com.mcorvera.vlogs.ejb.ReaderFacade;
import com.mcorvera.vlogs.ejb.UserFacade;
import com.mcorvera.vlogs.model.Blog;
import com.mcorvera.vlogs.model.BlogReader;
import com.mcorvera.vlogs.model.Reader;
import com.mcorvera.vlogs.model.User;
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
public class BlogReaderController {

    @EJB
    private BlogReaderFacade blogAndReaderRepository;
    @EJB
    private BlogFacade blogRepository;
    @EJB
    private ReaderFacade readerRepository;
    @EJB
    private UserFacade userRepository;

    private BlogReader blogReader = new BlogReader();
    private List<BlogReader> blogReaders = new ArrayList<BlogReader>();
    private List<Blog> blogs = new ArrayList<Blog>();
    private List<Reader> readers = new ArrayList<Reader>();
    private List<User> users = new ArrayList<User>();

    @PostConstruct
    public void loadRecords() {
        this.blogs = blogRepository.findAll();
        this.readers = readerRepository.findAll();
        this.users = userRepository.findAll();
        try {
            this.blogReaders = blogAndReaderRepository.getBlogReaderDetails();
            System.out.print(this.blogReaders);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadDetails(BlogReader blogReader) {
        this.blogReader = blogReader;
    }

    public void edit(BlogReader blogReader) {
        loadDetails(blogReader);
    }

    public String newRecord() {
        this.blogReader.reset();
        return "";
    }

    public void save() {
        try {
            blogAndReaderRepository.merge(blogReader);
            this.loadRecords();
            this.blogReader.reset();
            this.showMessage("Record saved");
        } catch (Exception e) {
            this.showMessage("Error at trying to create record");
        }
    }

    public void remove() {
        try {
            blogAndReaderRepository.remove(blogReader);
            this.loadRecords();
            this.blogReader.reset();
            this.showMessage("Record deleted");
        } catch (Exception e) {
            this.showMessage("Error at trying to delete record");
        }
    }

    public void showMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NOTICE", msg));
    }
}
