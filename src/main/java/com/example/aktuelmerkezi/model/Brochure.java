package com.example.aktuelmerkezi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Brochure {

    @Id
    private UUID id;
    private String link;
    private String name;

    public Brochure() {
    }

    public Brochure(String link, String name) {
        this.id = UUID.randomUUID();
        this.link = link;
        this.name = name;
    }

    public Brochure(UUID id, String link, String name) {
        this.id = id;
        this.link = link;
        this.name = name;
    }

    public UUID getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brochure brochure = (Brochure) o;
        return id.equals(brochure.id) &&
                link.equals(brochure.link) &&
                name.equals(brochure.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, name);
    }

    @Override
    public String toString() {
        return "Brochure{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
