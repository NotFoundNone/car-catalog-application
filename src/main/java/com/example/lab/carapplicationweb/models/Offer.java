package com.example.lab.carapplicationweb.models;

import com.example.lab.carapplicationweb.enums.Engine;
import com.example.lab.carapplicationweb.enums.Transmission;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseCreatedEntity{

    private String discription;

    private Engine engine;

    private String imageURL;

    private int mileage;

    private int price;

    private Transmission transmission;

    private int year;

    private Model model;

    private User seller;

    public Offer(LocalDate created,
                 LocalDate modified,
                 String description,
                 Engine engine,
                 String imageURL,
                 Integer milleage,
                 Integer price,
                 Transmission transmission,
                 Integer year,
                 Model model,
                 User seller) {
        super(created, modified);
        this.discription = description;
        this.engine = engine;
        this.imageURL = imageURL;
        this.mileage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model = model;
        this.seller = seller;
    }

    public Offer() {
    }

    @Column(name = "discription")
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Column(name = "engine")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Column(name = "imageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Column(name = "mileage")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "transmission")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "model_uuid", referencedColumnName = "uuid", nullable = false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
