package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.util.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;

public class EditBrandDto {

    private String uuid;

    @UniqueBrandName
    String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @NotEmpty(message = "Name of brand cannot be null or empty!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
