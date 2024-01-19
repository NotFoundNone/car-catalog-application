package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.services.dtos.AddBrandDto;
import com.example.lab.carapplicationweb.services.dtos.ShowBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.ShowDetailedBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.EditBrandDto;

import java.util.List;
import java.util.Optional;

public interface BrandService{

    void add(AddBrandDto brandDTO);

    void update(EditBrandDto newBrandDTO);

    ShowDetailedBrandInfoDto brandDetails(String brandName);

    void deleteByUuid(String uuid);

//    Optional<Brand> findByUuid(String uuid);

    Optional<EditBrandDto> findByUuid(String uuid);

    Optional<EditBrandDto> findByName(String brandName);

    List<ShowBrandInfoDto> getAll();

    void deleteByName(String brandName);
}
