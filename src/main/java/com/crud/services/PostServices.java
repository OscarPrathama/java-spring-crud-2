package com.crud.services;

import java.util.List;
import java.util.Optional;

import com.crud.entities.Post;
import com.crud.repositories.PostRepository;
import com.github.slugify.Slugify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServices {
    
    @Autowired
    PostRepository postRepository;

    public Iterable<Post> findAll(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Post> search(String keyword){
        if(keyword != null){
            return postRepository.search(keyword);
        }
        return postRepository.findAll();
    }

    public void save(Post post){
        
        // handle slug
        Slugify slug = new Slugify();
        String post_slug = slug.slugify(post.getPostSlug());
        post.setPostSlug(post_slug);

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
