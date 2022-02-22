package com.crud.services;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import com.crud.entities.User;
import com.crud.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Random;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Iterable<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Long userCount(){
        return userRepository.count();
    }

    /**
     * this code doesn't work
    */
    @PostConstruct
    public void initDB(){
        List<User> users = IntStream.rangeClosed(1, 0)
            .mapToObj(i -> new User(
                "user "+i, 
                "admin"+i, 
                "admin@gmail"+i+".com", 
                "123qweasd", 
                "admin"
                // new Random().nextInt(100)
            )).collect(Collectors.toList());
        
        userRepository.saveAll(users);
    }

}
