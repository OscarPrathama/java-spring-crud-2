package com.crud.services;

import com.crud.entities.Post;
import com.crud.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServices {
    
    @Autowired
    PostRepository postRepository;

    public Iterable<Post> findAll(){
        return postRepository.findAll();
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

}
