package com.mcorvera.vlogs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name="blogs_readers")
@Data
public class BlogReader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name="b_id")
    private Integer blogId;
    @Column(name="r_id")
    private Integer readerId;
     @Column(name="u_id")
    private Integer userId;
    
    @Transient
    private String readerName;
    @Transient
    private String title;
    @Transient
    private String code;
     
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id", referencedColumnName = "id", insertable = false, updatable = true)
    private Reader reader;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id", referencedColumnName = "id", insertable = false, updatable = true)
    private Blog blog;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", referencedColumnName = "id", insertable = false, updatable = true)
    private User user;

    public BlogReader reset() {
        return new BlogReader();
    }
}
