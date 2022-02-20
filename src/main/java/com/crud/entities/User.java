package com.crud.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")
    }    
)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name is required !")
    @Column(nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "username is required !")
    @Column(nullable = false, length = 100)
    private String username;

    @Email
    @NotEmpty(message = "email is required !")
    @Column(nullable = false, length = 100)
    private String email;

    @NotEmpty(message = "password is required !")
    @Size(min = 8, max = 16)
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String role = "default";

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
