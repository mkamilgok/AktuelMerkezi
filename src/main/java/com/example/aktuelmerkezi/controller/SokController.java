package com.example.aktuelmerkezi.controller;

import com.example.aktuelmerkezi.model.Brochure;
import com.example.aktuelmerkezi.service.SokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/sok")
public class SokController {

    private final SokService sokService;

    @Autowired
    public SokController(SokService sokService) {
        this.sokService = sokService;
    }

    @GetMapping
    public List<Brochure> getBrochures(){
        return sokService.getBrochures();
    }

    @PostMapping
    public Brochure addProduct(@RequestBody Brochure brochure){
        return sokService.addBrochure(brochure);
    }

    @PutMapping(path = "{id}")
    public Brochure updateProduct(@PathVariable("id") UUID id, @RequestBody Brochure brochure){
        return sokService.updateBrochure(id, brochure);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        sokService.deleteBrochure(id);
    }
}
