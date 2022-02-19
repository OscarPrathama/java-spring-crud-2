package com.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("posts")
public class PostController {
    
    /**
     * index
    */
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("posts", "hello posts");
        
        return "admin/users/index";
    }

    /**
     * create view
    */

    /**
     * store
    */

    /**
     * edit
    */

    /**
     * update
    */

    /**
     * delete
    */

}
