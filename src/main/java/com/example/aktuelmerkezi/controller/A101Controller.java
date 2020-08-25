package com.example.aktuelmerkezi.controller;


import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.A101Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/a101")
public class A101Controller {

    private final A101Repository a101Repository;

    @Autowired
    public A101Controller(A101Repository a101Repository){
        this.a101Repository = a101Repository;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        System.out.println("bu");
        return a101Repository.findAll();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") long id){
        return a101Repository.getOne(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return a101Repository.save(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        a101Repository.deleteById(id);
    }

}
