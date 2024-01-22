package com.example.lab.carapplicationweb.services.dtos;

import com.example.lab.carapplicationweb.util.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class EditBrandDto {

    private UUID uuid;

    @UniqueBrandName
    String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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
