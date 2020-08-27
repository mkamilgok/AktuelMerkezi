package com.example.aktuelmerkezi.service;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BimService {

    public final ProductRepository productRepository;

    public BimService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Bim"))
                .collect(Collectors.toList());
    }

    public Product getProduct(UUID id){
        return productRepository.getOne(id);
    }

    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        productRepository.deleteById(product.getId());
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id){
        productRepository.deleteById(id);
    }
}
