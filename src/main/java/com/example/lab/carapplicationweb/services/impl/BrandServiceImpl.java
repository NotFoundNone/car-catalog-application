package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.models.Brand;
import com.example.lab.carapplicationweb.repositories.BrandRepository;
import com.example.lab.carapplicationweb.services.BrandService;
import com.example.lab.carapplicationweb.services.dtos.AddBrandDto;
import com.example.lab.carapplicationweb.services.dtos.ShowBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.ShowDetailedBrandInfoDto;
import com.example.lab.carapplicationweb.services.dtos.EditBrandDto;
import com.example.lab.carapplicationweb.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
//@EnableCaching
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
//    @CacheEvict(cacheNames = "brands", allEntries = true)
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
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void update(EditBrandDto newBrandDTO) {
//        logger.info("Updating brand with UUID: {}", newBrandDTO.getUuid());

        if (!this.validationUtil.isValid(newBrandDTO))
        {
            this.validationUtil
                    .violations(newBrandDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Brand> existingBrand = brandRepository.findByUuid(newBrandDTO.getUuid());
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
//    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void deleteByUuid(String uuid) { brandRepository.deleteById(uuid);}

    @Override
    public Optional<EditBrandDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), EditBrandDto.class));
    }

    @Override
    public Optional<EditBrandDto> findByName(String brandName)
    {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByName(brandName), EditBrandDto.class));
    }

    @Override
//    @Cacheable("brands")
    public List<ShowBrandInfoDto> getAll() {
        try
        {
        Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowBrandInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
//    @CacheEvict(cacheNames = {"brands", "models", "offers"},  allEntries = true)
    public void deleteByName(String brandName) {
        brandRepository.deleteByName(brandName);
    }
}
