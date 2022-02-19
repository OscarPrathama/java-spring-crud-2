package com.crud.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String postTitle;

    @Column(nullable = false, length = 100, unique = true)
    private String postSlug;

    @Lob
    @Column(nullable = true)
    private String postContent;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
