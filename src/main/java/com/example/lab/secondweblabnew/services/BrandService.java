package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.services.dtos.AddBrandDto;
import com.example.lab.secondweblabnew.services.dtos.ShowDetailedBrandInfoDto;
import com.example.lab.secondweblabnew.services.dtos.UpdateBrandDto;

import java.util.List;
import java.util.Optional;

public interface BrandService{

    void add(AddBrandDto brandDTO);

    void update(UpdateBrandDto newBrandDTO);

    ShowDetailedBrandInfoDto brandDetails(String brandName);

    void deleteByUuid(String uuid);

//    Optional<Brand> findByUuid(String uuid);

    Optional<UpdateBrandDto> findByUuid(String uuid);

    List<Brand> getAll();

    void deleteByName(String brandName);
}
