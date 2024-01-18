package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.enums.Engine;

public class ShowOfferInfoDto {

    private String uuid;

    private String modelName;

//    private String brandName;

    private Engine engine;

    private int mileage;

    private int price;

    private int year;

    private String sellerUsername;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

//    public String getBrandName() { return brandName; }
//
//    public void setBrandName(String brandName) { this.brandName = brandName; }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
