package com.example.aktuelmerkezi.databaseLoader;

import com.example.aktuelmerkezi.model.Product;
import com.example.aktuelmerkezi.repository.BrochureRepository;
import com.example.aktuelmerkezi.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class BimDBUtilizer {

    private final ProductRepository productRepository;
    private final BrochureRepository brochureRepository;

    public BimDBUtilizer(ProductRepository productRepository, BrochureRepository brochureRepository) {
        this.productRepository = productRepository;
        this.brochureRepository = brochureRepository;
    }

    public void fetchProductsAndBrochuresOfBim() {
        String url = "https://www.bim.com.tr/default.aspx";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Element> productTemplates = doc.getElementsByClass("product big col-md-12 col-lg-12 col-xl-9 col-12");
        productTemplates.addAll(doc.getElementsByClass("product col-xl-3 col-lg-3 col-md-4 col-sm-6  col-12"));
        productTemplates.addAll(doc.getElementsByClass("product col-xl-3 col-lg-3 col-md-4 col-sm-6 col-12 LoadGroup0"));
        productTemplates.forEach(element -> {
            String name = element.getElementsByClass("subTitle").text().length() < 2
                        ? element.getElementsByClass("title").text()
                        : element.getElementsByClass("subTitle").text() + " - "  + element.getElementsByClass("title").text();
            String price = element.getElementsByClass("text quantify").text()
                            + element.getElementsByClass("number").text()
                            + element.getElementsByClass("curr").text();
            String link = "https://www.bim.com.tr" + element.getElementsByTag("a").attr("href");
            String imgLink =  "https://www.bim.com.tr"
                    + (element.getElementsByClass("img-fluid").attr("src").equals("") ?
                    element.getElementsByClass("img-fluid").attr("xsrc") :
                    element.getElementsByClass("img-fluid").attr("src"));
            imgLink = imgLink.replaceAll(" ", "%20");
            productRepository.save(new Product(name, price, link, imgLink, "Bim", "Bu hafta indirime girecekler"));
        });
    }
}
