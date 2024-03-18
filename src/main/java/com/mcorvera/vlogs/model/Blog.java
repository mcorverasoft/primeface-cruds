package com.mcorvera.vlogs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author miltoncorvera
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="blogs")
@Data
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    
    public void reset(){
        this.id=null;
        this.title="";
        this.description="";
    }
}
