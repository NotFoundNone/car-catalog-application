package com.example.lab.secondweblabnew.services.impl;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.repositories.BrandRepository;
import com.example.lab.secondweblabnew.services.BrandService;
import com.example.lab.secondweblabnew.services.dtos.AddBrandDto;
import com.example.lab.secondweblabnew.services.dtos.BrandDTO;
import com.example.lab.secondweblabnew.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
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
    public void update(String uuid, BrandDTO newBrandDTO) {

        if (!this.validationUtil.isValid(newBrandDTO))
        {
            this.validationUtil
                    .violations(newBrandDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Brand> existingBrand = brandRepository.findById(uuid);
                if (existingBrand == null)
                    throw new EntityNotFoundException("Бренд не найден");
                Brand brand = existingBrand.get();
                modelMapper.map(newBrandDTO, brand);
                brandRepository.saveAndFlush(brand);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) { brandRepository.deleteById(uuid);}

    @Override
    public Optional<Brand> findByUuid(String uuid) { return brandRepository.findById(uuid); }

    @Override
    public List<Brand> getAll() { return brandRepository.findAll();}
}
