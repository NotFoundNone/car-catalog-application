package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.services.dtos.BrandDTO;

import java.util.List;
import java.util.Optional;

public interface BrandService{

    void add(BrandDTO brandDTO);

    void update(String uuid, BrandDTO newBrandDTO);

    void deleteByUuid(String uuid);

    Optional<Brand> findByUuid(String uuid);

    List<Brand> getAll();

}
