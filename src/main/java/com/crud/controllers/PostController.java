package com.crud.controllers;

import java.util.List;

import javax.validation.Valid;

import com.crud.entities.Post;
import com.crud.services.PostServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
        System.out.println(postServices.findAll());

        return "admin/posts/index";
    }

    /**
     * view
    */
    @GetMapping("/view/{id}")
    public String view(@PathVariable( value = "id" ) Long id, Model model){
        Post post = postServices.getPostById(id);

        model.addAttribute("metaTitle", post.getPostTitle());
        model.addAttribute("post", post);

        return "admin/posts/view";
    }

    /**
     * search
    */
    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword){
        List<Post> searchPosts = postServices.search(keyword);

        model.addAttribute("metaTitle", "Search post");
        model.addAttribute("tableClass", "posts-table");
        model.addAttribute("keyword", keyword);
        model.addAttribute("posts", searchPosts);

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
    public String store(@Valid @ModelAttribute("post") Post post, BindingResult result) {

        if (result.hasErrors()) {
            return "/admin/posts/create";
        }

        postServices.save(post);

        return "redirect:/admin/posts/edit/"+post.getId();
    }
    

    /**
     * edit view
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model){
        Post post = postServices.getPostById(id);

        model.addAttribute("metaTitle", "Edit post");
        model.addAttribute("post", post);

        return "admin/posts/edit";
    }

    /**
     * update
    */
    @PutMapping("update")
    public String update(@ModelAttribute("post") Post post) {

        postServices.save(post);
        
        return "redirect:/admin/posts/edit/"+post.getId();
    }
    
    /**
     * delete
    */
    // @GetMapping("/delete/{id}")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {

        try {
            postServices.delete(id);
            return "redirect:/admin/posts";
        } catch (Exception e) {
            return "Failed to delete";
        }
        
    }

}
