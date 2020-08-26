package com.example.aktuelmerkezi.controller;


import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.a101Repository.BestProductsOfWeekRepository;
import com.example.aktuelmerkezi.service.A101Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/a101")
public class A101Controller {

    private final BestProductsOfWeekRepository bestProductsOfWeekRepository;
    private final A101Service a101Service;

    @Autowired
    public A101Controller(BestProductsOfWeekRepository bestProductsOfWeekRepository, A101Service a101Service){
        this.bestProductsOfWeekRepository = bestProductsOfWeekRepository;
        this.a101Service = a101Service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return a101Service.getBestProductsOfWeek();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") long id){
        return bestProductsOfWeekRepository.getOne(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return bestProductsOfWeekRepository.save(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        bestProductsOfWeekRepository.deleteById(id);
    }

}
