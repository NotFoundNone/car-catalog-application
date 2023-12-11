package com.example.lab.secondweblabnew.services.impl;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.repositories.BrandRepository;
import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.dtos.AddBrandDto;
import com.example.lab.secondweblabnew.services.dtos.ShowDetailedBrandInfoDto;
import com.example.lab.secondweblabnew.services.dtos.UpdateBrandDto;
import com.example.lab.secondweblabnew.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private final ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository){ this.brandRepository = brandRepository; }

    @Override
    public void add(AddBrandDto brandDTO)
    {
        if (!this.validationUtil.isValid(brandDTO))
        {
            this.validationUtil
                    .violations(brandDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                this.brandRepository
                        .saveAndFlush(this.modelMapper.map(brandDTO, Brand.class));
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void update(UpdateBrandDto newBrandDTO) {

        if (!this.validationUtil.isValid(newBrandDTO))
        {
            this.validationUtil
                    .violations(newBrandDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Brand> existingBrand = brandRepository.findByName(newBrandDTO.getName());
                Brand brand = existingBrand.get();
                modelMapper.map(newBrandDTO, brand);
                brandRepository.saveAndFlush(brand);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public ShowDetailedBrandInfoDto brandDetails(String brandName)
    {
        return modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowDetailedBrandInfoDto.class);
    }

    @Override
    public void deleteByUuid(String uuid) { brandRepository.deleteById(uuid);}

    @Override
    public Optional<UpdateBrandDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), UpdateBrandDto.class));
    }

    @Override
    public List<Brand> getAll() { return brandRepository.findAll();}

    @Override
    public void deleteByName(String brandName) {
        brandRepository.deleteByName(brandName);
    }
}
