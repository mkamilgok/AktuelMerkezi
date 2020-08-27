package com.example.aktuelmerkezi.repository;

import com.example.aktuelmerkezi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {
}
