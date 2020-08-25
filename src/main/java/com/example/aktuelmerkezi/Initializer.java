package com.example.aktuelmerkezi;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.A101Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final A101Repository a101Repository;

    @Autowired
    public Initializer(A101Repository a101Repository) {
        this.a101Repository = a101Repository;
    }


    @Override
    public void run(String... args) throws Exception {


        String blogUrl = "https://www.a101.com.tr/haftanin-yildizlari/";
            Document doc = Jsoup.connect(blogUrl).get();
            List<Element> productTemplates = doc.getElementsByClass("product-card js-product-wrapper");
            productTemplates.forEach(element -> {
                String name = element.getElementsByClass("name").text();
                String price = element.getElementsByClass("current").text();
                String link = "https://www.a101.com.tr" + element.getElementsByClass("name-price").attr("href");
                String imgLink = element.getElementsByAttribute("data-src").attr("data-src");
                a101Repository.save(new Product(name, price, link, imgLink));
            });

    }
}
