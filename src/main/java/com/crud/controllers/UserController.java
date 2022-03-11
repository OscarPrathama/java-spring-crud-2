package com.crud.controllers;

import javax.validation.Valid;

import com.crud.entities.User;
import com.crud.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/users")
public class UserController {
    
    @Autowired
    UserService userService;

    /**
     * Display users
     * 
     * @return String
    */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("metaTitle", "Users Management");
        model.addAttribute("tableClass", "users-table");
        model.addAttribute("bodyClass", "users-index");

        return "admin/users/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("metaTitle", "Create user");

        return "admin/users/create";

    }
    

    /**
     * 
     * @param user
     * @param result
     * 
     * @return String
    */
    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("user") User user, BindingResult result) {
        
        if( result.hasErrors() ){
            return "/admin/users/create";
        }
        
        userService.save(user);

        return "redirect:/admin/users/edit/"+user.getId();
    }

}
