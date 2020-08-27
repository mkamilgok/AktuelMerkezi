package com.example.aktuelmerkezi.databaseLoader;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class A101DBUtilizer {

    private final ProductRepository productRepository;

    public A101DBUtilizer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void fetchProductsOfA101() {
        fetchOnSaleProducts();
        fetchBestProductsOfWeekA101();
    }

    private void fetchOnSaleProducts() {
        String urlRoot = "https://www.a101.com.tr/aldin-aldin/?sorter=-price&page=";
        int pageNum = 1;
        Document doc = null;

        // Loop provides fetching products from the next pages till the end
        do{
            try {
                doc = Jsoup.connect(urlRoot + pageNum).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Element> productTemplates = doc.getElementsByClass("product-card js-product-wrapper");
            productTemplates.forEach(element -> {
                String name = element.getElementsByClass("name").text();
                String price = element.getElementsByClass("current").text();
                String link = "https://www.a101.com.tr" + element.getElementsByClass("name-price").attr("href");
                String imgLink = element.getElementsByAttribute("data-src").attr("data-src");
                productRepository.save(new Product(name, price, link, imgLink, "a101-on sale products", "Bu hafta gelenler"));
            });
            pageNum++;
        } while(doc.getElementsByClass("page-link js-pagination-next ").size() != 0);
    }

    private void fetchBestProductsOfWeekA101() {
        String brochuresUrl = "https://www.a101.com.tr/afisler";
        Document docOfBrochures = null;
        try {
            docOfBrochures = Jsoup.connect(brochuresUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dateExplanation = docOfBrochures.getElementsByAttributeValue("href", "/afisler-haftanin-yildizlari").select("span").text();


        String url = "https://www.a101.com.tr/haftanin-yildizlari/";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Element> productTemplates = doc.getElementsByClass("product-card js-product-wrapper");
        productTemplates.forEach(element -> {
            String name = element.getElementsByClass("name").text();
            String price = element.getElementsByClass("current").text();
            String link = "https://www.a101.com.tr" + element.getElementsByClass("name-price").attr("href");
            String imgLink = element.getElementsByAttribute("data-src").attr("data-src");
            productRepository.save(new Product(name, price, link, imgLink, "a101-best products", dateExplanation));
        });
    }

}
