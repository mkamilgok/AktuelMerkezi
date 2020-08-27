package com.example.aktuelmerkezi.service;

import com.example.aktuelmerkezi.model.Brochure;
import com.example.aktuelmerkezi.repository.BrochureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SokService {

    private final BrochureRepository brochureRepository;

    @Autowired
    public SokService(BrochureRepository brochureRepository) {
        this.brochureRepository = brochureRepository;
    }

    public List<Brochure> getBrochures(){
        return brochureRepository.findAll()
                .stream()
                .filter(brochure -> (brochure.getLink().contains("sok") || brochure.getName().contains("sok")))
                .collect(Collectors.toList());
    }

    public Brochure addBrochure(Brochure brochure){
        return brochureRepository.save(new Brochure(brochure.getLink(), "sok-" + brochure.getName()));
    }


    public Brochure updateBrochure(UUID id, Brochure brochure){
        brochureRepository.deleteById(id);
        return brochureRepository.save(new Brochure(id, brochure.getLink(), "sok-" + brochure.getName()));
    }

    public void deleteBrochure(UUID id){
        brochureRepository.deleteById(id);
    }

}
