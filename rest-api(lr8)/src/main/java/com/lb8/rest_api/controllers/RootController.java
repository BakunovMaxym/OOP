package com.lb8.rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.lb8.rest_api.persistence.model.Product;
import com.lb8.rest_api.service.ProductService;


@Controller
public class RootController {

    private final ProductService productService;

    @Autowired
    public RootController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping({"/", "/home"})
    public String getRootPath(Model model){
        final List<Product> products = productService.readAll();
        System.out.println(products.size());
        model.addAttribute("items", products);
        return "home";
    }

}
