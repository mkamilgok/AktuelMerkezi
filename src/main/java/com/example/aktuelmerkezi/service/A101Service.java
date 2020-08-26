package com.example.aktuelmerkezi.service;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.a101Repository.BestProductsOfWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class A101Service {

    private final BestProductsOfWeekRepository bestProductsOfWeekRepository;

    @Autowired
    public A101Service(BestProductsOfWeekRepository bestProductsOfWeekRepository) {
        this.bestProductsOfWeekRepository = bestProductsOfWeekRepository;
    }

    public List<Product> getBestProductsOfWeek(){
        return bestProductsOfWeekRepository.findAll();
    }
}
