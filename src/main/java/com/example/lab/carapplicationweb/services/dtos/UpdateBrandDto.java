package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.util.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;

public class UpdateBrandDto {

    @UniqueBrandName
    String name;

    @NotEmpty(message = "Name of brand cannot be null or empty!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
