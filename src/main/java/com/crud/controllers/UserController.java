package com.crud.controllers;

import com.crud.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("")
    public String index(){
        userService.initDB();

        return "admin/users/index";
    }

}
