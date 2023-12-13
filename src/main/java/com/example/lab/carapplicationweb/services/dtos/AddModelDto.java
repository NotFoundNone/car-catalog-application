package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.enums.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddModelDto {

    private String name;

    private Category category;

    private int startYear;

    private int endYear;

    private String brandName;

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
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
