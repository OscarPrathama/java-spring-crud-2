package com.crud.services;

import com.crud.entities.User;
import com.crud.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Iterable<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

}
