package com.example.aktuelmerkezi.databaseLoader;

import com.example.aktuelmerkezi.model.Brochure;
import com.example.aktuelmerkezi.repository.BrochureRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Component
public class SokDBUtilizer {

    public final BrochureRepository brochureRepository;

    public SokDBUtilizer(BrochureRepository brochureRepository) {
        this.brochureRepository = brochureRepository;
    }

    public void fetchBrochuresOfSok() {
        String firsatlarRedirectedUrl = getRedirectedUrl("https://kurumsal.sokmarket.com.tr/firsatlar/carsamba/");
        Brochure firsatlar = new Brochure(firsatlarRedirectedUrl, "firsatlar");
        brochureRepository.save(firsatlar);

        String hSonuFirsatlariRedirectedUrl = getRedirectedUrl("https://kurumsal.sokmarket.com.tr/firsatlar/hafta-sonu/");
        Brochure hSonuFirsatlari = new Brochure(hSonuFirsatlariRedirectedUrl, "haftasonu-firsatlar");
        brochureRepository.save(hSonuFirsatlari);
    }

    private String getRedirectedUrl(String url) {
        try {
            URLConnection con = new URL(url).openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            is.close();
            return con.getURL().toString();
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}
