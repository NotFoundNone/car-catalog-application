package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.services.dtos.AddBrandDto;
import com.example.lab.carapplicationweb.services.dtos.ShowBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.ShowDetailedBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.EditBrandDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandService{

    List<ShowBrandInfoDto> getAll();

    Optional<EditBrandDto> findByUuid(UUID uuid);

    Optional<EditBrandDto> findByName(String brandName);

    ShowDetailedBrandInfoDto brandDetails(String brandName);

    void add(AddBrandDto brandDTO);

    void update(EditBrandDto newBrandDTO);

    void deleteByUuid(UUID uuid);

    void deleteByName(String brandName);

}
