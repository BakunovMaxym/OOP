package com.lb8.rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lb8.rest_api.persistence.model.Product;
import com.lb8.rest_api.service.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @PostMapping("/products")
    public ResponseEntity<String> create(@RequestBody Product product) {
        productService.create(product);
        System.out.println(product);
        return new ResponseEntity<>("Successfuly added", HttpStatus.CREATED);
    }


    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> read() {
       final List<Product> products = productService.readAll();
       System.out.println(products);
       return products != null &&  !products.isEmpty()
       ? new ResponseEntity<>(products, HttpStatus.OK)
       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> read(@PathVariable(name = "id") Long id) {
        final Product product = productService.read(id);

       return product != null
               ? new ResponseEntity<>(product, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/products/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") long id, @RequestBody Product product) {
        final boolean updated = productService.update(product, id);
    
        return updated
                ? new ResponseEntity<>("Successfuly edited", HttpStatus.OK)
                : new ResponseEntity<>("Error trying to edit product", HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
       final boolean deleted = productService.delete(id);

       return deleted
               ? new ResponseEntity<>("Successfuly deleted", HttpStatus.OK)
               : new ResponseEntity<>("Error trying to delete product", HttpStatus.NOT_MODIFIED);
    }
}
