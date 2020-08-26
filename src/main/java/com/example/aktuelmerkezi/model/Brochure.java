package com.example.aktuelmerkezi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Brochure {

    @Id
    @GeneratedValue
    private long id;
    private String link;
    private String name;

    public Brochure() {
    }

    public Brochure(long id, String link, String name) {
        this.id = id;
        this.link = link;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
