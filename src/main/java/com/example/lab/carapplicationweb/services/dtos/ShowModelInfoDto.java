package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.enums.Category;

public class ShowModelInfoDto {

    private String name;

    private Category category;

    private String brandName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
