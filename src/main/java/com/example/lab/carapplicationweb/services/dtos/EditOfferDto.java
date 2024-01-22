package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.enums.Engine;
import com.example.lab.carapplicationweb.enums.Transmission;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class EditOfferDto {

    private UUID uuid;

    private String discription;

    private Engine engine;

    private Integer mileage;

    private Integer price;

    private Transmission transmission;

    private Integer year;

    private String modelName;

    private String sellerUsername;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price of model cannot be null or empty!")
    @Min(value = 1, message = "Price should be at least 1 characters long!")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "The year must be less than or equal to 2100.")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @NotNull(message = "Please choose a model!")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @NotNull(message = "Please choose a seller!")
    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
