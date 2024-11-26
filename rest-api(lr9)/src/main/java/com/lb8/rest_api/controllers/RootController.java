package com.lb8.rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.ui.Model;

@Controller
public class RootController {

    @Autowired

    @GetMapping({"/", "/home"})
    public String getRootPath(){
        return "home";
    }

}
