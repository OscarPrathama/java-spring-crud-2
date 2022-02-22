package com.crud.services;

import java.util.List;
import java.util.Optional;

import com.crud.entities.Post;
import com.crud.repositories.PostRepository;
import com.github.slugify.Slugify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServices {
    
    @Autowired
    PostRepository postRepository;

    public Iterable<Post> findAll(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Long postCount(){
        return postRepository.count();
    }

    public List<Post> search(String keyword){
        if(keyword != null){
            return postRepository.search(keyword);
        }
        return postRepository.findAll();
    }

    public List<Post> findPostWithSorting(String field){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public Page<Post> findPostsWithPagination(int offset, int pageSize){
        Page<Post> posts = postRepository.findAll(PageRequest.of(offset, pageSize));
        return posts;
    }

    public Page<Post> findPostsWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Post> posts = postRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return posts;
    }

    // used on a method that needs to be executed after dependency injection is done to perform any initialization. 
    // This method MUST be invoked before the class is put into service. 
    // This annotation MUST be supported on all classes that support dependency injection.
    // @PostConstruct
    // public void initDB(){
    //     List<Post> posts = IntStream.rangeClosed(1, 200)
    //         .mapToObj(i -> new Post(
    //             "post" + i,
    //             1,
    //             "post-"+i,
    //             "post",
    //             "publish",
    //             "lorem ipsum dolor askimet",
    //             new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
    //             new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())
    //             // new Random().nextInt(100)
    //         )).collect(Collectors.toList());
        
    //     postRepository.saveAll(posts);
    // }

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
