package com.example.aktuelmerkezi;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public Initializer(ProductRepository productRepository) {
        this.productRepository = productRepository;;
    }

    @Override
    public void run(String... args) throws Exception {
        fetchProductsOfA101();
    }

    private String[] getDatesOfA101(){
        String brochuresUrl = "https://www.a101.com.tr/afisler";
        Document doc = null;
        try {
            doc = Jsoup.connect(brochuresUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] dates = new String[3];
        dates[0] = doc.getElementsByAttributeValue("href", "/afisler-haftanin-yildizlari").select("span").text();
        dates[1] = doc.getElementsByAttributeValue("href", "/afisler-aldin-aldin").select("span").text();
        dates[2] = doc.getElementsByAttributeValue("href", "/afisler-aldin-aldin-2").select("span").text();

        return dates;
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

    private void fetchBestProductsOfWeekA101(String dateExplanation) {
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

    private void fetchNextWeekBrochureA101(String dateExplanation) {

    }

    private void fetchCurrentWeekBrochureA101(String dateExplanation) {
    }

    private void fetchProductsOfA101() {
        String[] datesOfA101 = getDatesOfA101();

        String dateOfBestProducts = datesOfA101[0];
        fetchBestProductsOfWeekA101(dateOfBestProducts);

        String dateOfNextWeek = datesOfA101[1];
        fetchNextWeekBrochureA101(dateOfNextWeek);

        String dateOfCurrentWeek = datesOfA101[2];
        fetchCurrentWeekBrochureA101(dateOfCurrentWeek);

        fetchOnSaleProducts();
    }

}
