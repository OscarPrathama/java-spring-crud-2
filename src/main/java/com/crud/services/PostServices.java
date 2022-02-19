package com.crud.services;

import java.util.List;
import java.util.Optional;

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

    public List<Post> search(String keyword){
        if(keyword != null){
            return postRepository.search(keyword);
        }
        return postRepository.findAll();
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public Post getPostById(Long id){
        Optional<Post> optional = postRepository.findById(id);
        Post post = null;
        if(optional.isPresent()){
            post = optional.get();
        }else{
            throw new RuntimeException("Post with id "+id+" not found");
        }
        return post;
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

}
