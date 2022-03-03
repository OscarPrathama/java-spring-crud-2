package com.crud.services;

import com.crud.entities.User;
import com.crud.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<User> search(String keyword){
        if(keyword != null){
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("User with id "+id+" not found");
        }
        return user;
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
