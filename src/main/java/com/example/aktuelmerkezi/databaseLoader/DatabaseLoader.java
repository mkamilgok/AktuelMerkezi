package com.example.aktuelmerkezi.databaseLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final BimDBUtilizer bimDBUtilizer;
    private final A101DBUtilizer a101DBUtilizer;
    private final SokDBUtilizer sokDBUtilizer;

    @Autowired
    public DatabaseLoader(BimDBUtilizer bimDBUtilizer, A101DBUtilizer a101DBUtilizer, SokDBUtilizer sokDBUtilizer) {
        this.bimDBUtilizer = bimDBUtilizer;
        this.a101DBUtilizer = a101DBUtilizer;
        this.sokDBUtilizer = sokDBUtilizer;
    }

    @Override
    public void run(String... args) throws Exception {
        bimDBUtilizer.fetchProductsAndBrochuresOfBim();
        a101DBUtilizer.fetchProductsOfA101();
        sokDBUtilizer.fetchBrochuresOfSok();
    }

}
