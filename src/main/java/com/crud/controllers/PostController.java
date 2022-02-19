package com.crud.controllers;

import com.crud.entities.Post;
import com.crud.services.PostServices;
import com.github.slugify.Slugify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;;


@Controller
@RequestMapping("admin/posts")
public class PostController {
    
    @Autowired
    private PostServices postServices;

    /**
     * index
    */
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("metaTitle", "Posts");
        model.addAttribute("posts", postServices.findAll());

        return "admin/posts/index";
    }

    /**
     * create view
    */
    @GetMapping("/create")
    public String create(Model model) {
        Post post = new Post();
        
        model.addAttribute("metaTitle", "Create post");
        model.addAttribute("post", post);

        return "admin/posts/create";
    }
    

    /**
     * store
    */
    @PostMapping("/store")
    public String store(@ModelAttribute("post") Post post) {
        
        Slugify slug = new Slugify();
        String post_slug = slug.slugify(post.getPostTitle());
        System.out.println("coba "+post_slug);
        System.out.println("coba "+post.getPostTitle());
        post.setPostSlug(post_slug);

        postServices.save(post);

        return "redirect:/admin/posts";
    }
    

    /**
     * edit
    */
    

    /**
     * update
    */

    /**
     * delete
    */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        postServices.delete(id);
        
        return "redirect:/admin/posts";
    }

}
