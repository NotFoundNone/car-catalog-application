package com.example.lab.secondweblabnew.services.dtos;

import com.example.lab.secondweblabnew.enums.Category;
import jakarta.validation.constraints.*;

public class AddModelDto {

    private String name;

    private Category category;

    private int startYear;

    private int endYear;

    private BrandDTO brand;

    @NotNull(message = "Name of model cannot be null or empty!")
    @Size(min = 1, message = "Model name should be at least 1 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Please choose a category!")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull(message = "Start Year cannot be null or empty!")
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @NotNull(message = "End Year cannot be null or empty!")
    @Max(value = 2100, message = "The year must be less than or equal to 2100.")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @NotNull(message = "Please choose a brand!")
    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }
}
