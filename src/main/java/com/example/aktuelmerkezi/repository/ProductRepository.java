package com.example.aktuelmerkezi.repository;

import com.example.aktuelmerkezi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}