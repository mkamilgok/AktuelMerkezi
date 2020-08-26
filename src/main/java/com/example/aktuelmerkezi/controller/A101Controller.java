package com.example.aktuelmerkezi.controller;


import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.service.A101Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/a101")
public class A101Controller {

    private final A101Service a101Service;

    @Autowired
    public A101Controller( A101Service a101Service){
        this.a101Service = a101Service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        //return a101Service.getBestProductsOfWeek();
        return a101Service.getOnSaleProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") long id){
        return a101Service.getProduct(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return a101Service.addNewProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        a101Service.deleteProduct(id);
    }

}
