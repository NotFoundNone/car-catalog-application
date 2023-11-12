package com.example.lab.secondweblabnew.models;

import com.example.lab.secondweblabnew.enums.Engine;
import com.example.lab.secondweblabnew.enums.Transmission;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @ManyToOne(optional = false)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "model_uuid", referencedColumnName = "uuid", nullable = false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    @ManyToOne(optional = false)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
