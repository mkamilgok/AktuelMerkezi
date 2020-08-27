package com.example.aktuelmerkezi.controller;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.service.A101Service;
import com.example.aktuelmerkezi.service.BimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/bim")
public class BimController {

    private final BimService bimService;

    @Autowired
    public BimController(BimService bimService) {
        this.bimService = bimService;
    }

    @GetMapping
    public List<Product> getBestProductsOfWeek(){
        return bimService.getProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") UUID id){
        return bimService.getProduct(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return bimService.addNewProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return bimService.updateProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        bimService.deleteProduct(id);
    }

}
