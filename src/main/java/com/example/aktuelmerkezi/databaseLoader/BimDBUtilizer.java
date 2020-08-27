package com.example.aktuelmerkezi.databaseLoader;

import com.example.aktuelmerkezi.repository.BrochureRepository;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class BimDBUtilizer {

    private final ProductRepository productRepository;
    private final BrochureRepository brochureRepository;

    public BimDBUtilizer(ProductRepository productRepository, BrochureRepository brochureRepository) {
        this.productRepository = productRepository;
        this.brochureRepository = brochureRepository;
    }

    public void fetchProductsAndBrochuresOfBim() {
    }
}
