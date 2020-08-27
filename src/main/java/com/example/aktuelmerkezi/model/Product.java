package com.example.aktuelmerkezi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    private UUID id;
    @NonNull
    private String name;
    private String price;
    private String link;
    private String imgLink;
    @NonNull
    private String category;
    private String dateExplanation;

    public Product() {
    }

    public Product(String name, String price, String link, String imgLink, String category, String dateExplanation) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.link = link;
        this.imgLink = imgLink;
        this.dateExplanation = dateExplanation;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDateExplanation() {
        return dateExplanation;
    }

    public void setDateExplanation(String dateExplanation) {
        this.dateExplanation = dateExplanation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                link.equals(product.link) &&
                imgLink.equals(product.imgLink) &&
                category.equals(product.category) &&
                dateExplanation.equals(product.dateExplanation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, link, imgLink, category, dateExplanation);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", category='" + category + '\'' +
                ", dateExplanation='" + dateExplanation + '\'' +
                '}';
    }
}
