package com.example.lab.secondweblabnew.services.impl;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.models.Model;
import com.example.lab.secondweblabnew.repositories.ModelRepository;
import com.example.lab.secondweblabnew.services.ModelService;
import com.example.lab.secondweblabnew.services.dtos.ModelDTO;
import com.example.lab.secondweblabnew.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private ModelRepository modelRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository (ModelRepository modelRepository){ this.modelRepository = modelRepository; }

    @Override
    public void add(ModelDTO modelDTO) {
        if (!this.validationUtil.isValid(modelDTO))
        {
            this.validationUtil
                    .violations(modelDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                this.modelRepository
                        .saveAndFlush(this.modelMapper.map(modelDTO, Model.class));
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void update(String uuid, ModelDTO newModelDTO) {
        if (!this.validationUtil.isValid(newModelDTO))
        {
            this.validationUtil
                    .violations(newModelDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Model> existingBrand = modelRepository.findById(uuid);
                if (existingBrand == null)
                    throw new EntityNotFoundException("Model not found!");
                Model model = existingBrand.get();
                modelMapper.map(newModelDTO, model);
                modelRepository.saveAndFlush(model);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) { modelRepository.deleteById(uuid); }

    @Override
    public Optional<Model> findByUuid(String uuid){ return modelRepository.findById(uuid); }

    @Override
    public List<Model> getAll() { return modelRepository.findAll(); }
}