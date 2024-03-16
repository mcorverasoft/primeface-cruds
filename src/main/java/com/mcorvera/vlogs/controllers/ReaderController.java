/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcorvera.vlogs.controllers;

import com.mcorvera.vlogs.ejb.ReaderFacade;
import com.mcorvera.vlogs.ejb.RepositoryFacade;
import com.mcorvera.vlogs.model.Reader;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
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
public class ReaderController {
    

    @EJB
    private ReaderFacade readerRepository;
    private List<Reader> readers = new ArrayList<Reader>();
    private Reader reader= new Reader();
    
    @PostConstruct
    public void loadRecords(){
            this.readers=readerRepository.findAll();
    }
    
    public String newRecord(){
        this.reader.reset();
        return "";
    }
    
    public void save(){
        try{
            readerRepository.merge(reader);
            this.loadRecords();
            this.reader.reset();
            this.showMessage("Record saved");
        }catch(Exception e){
            this.showMessage("Error at trying to create record");
        }
    }
    
    public void remove(){
        try{
            this.loadRecords();
            readerRepository.remove(reader);
            this.reader.reset();
            this.showMessage("Record deleted");
        }catch(Exception e){
            this.showMessage("Error at trying to delete record");
        }
    }
    
    public void showMessage(String msg){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NOTICE", msg));
    }
}
