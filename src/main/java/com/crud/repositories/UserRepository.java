package com.crud.repositories;

import java.util.List;

import com.crud.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT p FROM User p WHERE p.name LIKE %?1%")
    public List<User> search(@Param("keyword") String keyword);

}
