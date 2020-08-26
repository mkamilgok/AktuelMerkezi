package com.example.aktuelmerkezi.repository.a101Repository;

import com.example.aktuelmerkezi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BestProductsOfWeekRepository extends JpaRepository<Product, Long> {
}
