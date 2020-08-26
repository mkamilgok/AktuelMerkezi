package com.example.aktuelmerkezi;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.a101Repository.BestProductsOfWeekRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    private final BestProductsOfWeekRepository bestProductsOfWeekRepository;

    @Autowired
    public Initializer(BestProductsOfWeekRepository bestProductsOfWeekRepository) {
        this.bestProductsOfWeekRepository = bestProductsOfWeekRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        fetchProductsOfA101();
    }

    private void fetchProductsOfA101() {
        String brochuresUrl = "https://www.a101.com.tr/afisler";
        Document doc = null;
        try {
            doc = Jsoup.connect(brochuresUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dateOfBestProucts = doc.getElementsByAttributeValue("href", "/afisler-haftanin-yildizlari").select("span").text();
        String dateOfNextWeek = doc.getElementsByAttributeValue("href", "/afisler-aldin-aldin").select("span").text();
        String dateOfCurrentWeek = doc.getElementsByAttributeValue("href", "/afisler-aldin-aldin-2").select("span").text();
        fetchBestProductsOfWeekA101(dateOfBestProucts);
        fetchNextWeekProductsA101(dateOfNextWeek);
        fetchCurrentWeekProductsA101(dateOfCurrentWeek);
    }

    private void fetchNextWeekProductsA101(String dateExplanation) {
    }

    private void fetchCurrentWeekProductsA101(String dateExplanation) {
    }

    private void fetchBestProductsOfWeekA101(String dateExplanation) {
        String blogUrl = "https://www.a101.com.tr/haftanin-yildizlari/";
        Document doc = null;
        try {
            doc = Jsoup.connect(blogUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Element> productTemplates = doc.getElementsByClass("product-card js-product-wrapper");
        productTemplates.forEach(element -> {
            String name = element.getElementsByClass("name").text();
            String price = element.getElementsByClass("current").text();
            String link = "https://www.a101.com.tr" + element.getElementsByClass("name-price").attr("href");
            String imgLink = element.getElementsByAttribute("data-src").attr("data-src");
            bestProductsOfWeekRepository.save(new Product(name, price, link, imgLink, dateExplanation));
        });
    }
}
