package com.example.aktuelmerkezi.controller;


import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.service.A101Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/a101")
public class A101Controller {

    private final A101Service a101Service;

    @Autowired
    public A101Controller( A101Service a101Service){
        this.a101Service = a101Service;
    }

    @GetMapping
    //(value = "/bestOfTheWeek") ekle
    public List<Product> getBestProductsOfWeek(){
        return a101Service.getBestProductsOfWeek();
    }

    @GetMapping(path = "/onSale")
    public List<Product> getOnSaleProducts(){
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

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return a101Service.updateProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        a101Service.deleteProduct(id);
    }

}
