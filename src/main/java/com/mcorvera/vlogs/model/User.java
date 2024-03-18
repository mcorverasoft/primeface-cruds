package com.mcorvera.vlogs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author miltoncorvera
 */
@NoArgsConstructor
@Entity
@Table(name="users")
@Data
@NamedQueries({
    @NamedQuery(name = "User.findByCodeAndPassword", query = "SELECT u FROM User u WHERE (u.code = :codeOrEmail or u.email = :codeOrEmail) and u.password= :password"),
    @NamedQuery(name = "User.findByCodeOrEmail", query = "SELECT u FROM User u WHERE (u.code = :codeOrEmail or u.email = :codeOrEmail)")
})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String email;
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;
    private String name;
    public User reset() {
        return new User();
    }
    
}
