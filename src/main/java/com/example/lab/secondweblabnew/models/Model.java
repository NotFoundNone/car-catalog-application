package com.example.lab.secondweblabnew.models;

import com.example.lab.secondweblabnew.enums.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@Table(name = "models")
public class Model extends BaseCreatedEntity{

    private String name;

    private Category category;

    private String imageURL;

    private int startYear;

    private int endYear;

    private Brand brand;

    private Set<Offer> offers;

    public Model() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "imageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Column(name = "startYear")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "endYear")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "brand_uuid",referencedColumnName = "uuid", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "model", cascade = CascadeType.REMOVE)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offer) {
        this.offers = offer;
    }
}
