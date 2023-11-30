package com.example.lab.secondweblabnew.services.dtos;

import com.example.lab.secondweblabnew.enums.Engine;
import com.example.lab.secondweblabnew.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddOfferDto {

    private String discription;

    private Engine engine;

    private int mileage;

    private int price;

    private Transmission transmission;

    private int year;

    private ModelDTO model;

    private UserDTO seller;

    @NotNull(message = "Discription of model cannot be null or empty!")
    @Size(min = 10, message = "Discription should be at least 1 characters long!")
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @NotNull(message = "Please choose a engine!")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @NotNull(message = "Mileage of model cannot be null or empty!")
    @Min(value = 1, message = "Mileage should be at least 1 characters long!")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price of model cannot be null or empty!")
    @Min(value = 1, message = "Price should be at least 1 characters long!")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NotNull(message = "Please choose a transmission!")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @NotNull(message = "Year of model cannot be null or empty!")
    @Min(value = 1, message = "Year should be at least 1 characters long!")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotNull(message = "Please choose a model!")
    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    @NotNull(message = "Please choose a seller!")
    public UserDTO getSeller() {
        return seller;
    }

    public void setSeller(UserDTO seller) {
        this.seller = seller;
    }
}
