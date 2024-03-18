package com.mcorvera.vlogs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author miltoncorvera
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="readers")
@Data
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    public void reset(){
        this.id=null;
        this.name="";
    }
}
