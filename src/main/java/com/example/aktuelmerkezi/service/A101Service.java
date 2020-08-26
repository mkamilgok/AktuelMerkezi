package com.example.aktuelmerkezi.service;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class A101Service {

    private final ProductRepository productRepository;

    @Autowired
    public A101Service(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getBestProductsOfWeek(){
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("a101-best products"))
                .collect(Collectors.toList());
    }

    public List<Product> getOnSaleProducts(){
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("a101-on sale products"))
                .collect(Collectors.toList());
    }

    public Product getProduct(long id){
        return productRepository.getOne(id);
    }

    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

}
