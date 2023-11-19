package com.example.lab.secondweblabnew.services.impl;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.repositories.OfferRepository;
import com.example.lab.secondweblabnew.services.OfferService;
import com.example.lab.secondweblabnew.services.dtos.OfferDTO;
import com.example.lab.secondweblabnew.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferRepository (OfferRepository offerRepository) { this.offerRepository = offerRepository;}

    @Override
    public void add(OfferDTO offerDTO) {
        if (!validationUtil.isValid(offerDTO))
        {
            this.validationUtil
                    .violations(offerDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                this.offerRepository.saveAndFlush(this.modelMapper.map(offerDTO, Offer.class));
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void update(String uuid, OfferDTO newOfferDTO) {
        if (!validationUtil.isValid(newOfferDTO)) {
            this.validationUtil
                    .violations(newOfferDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Offer> existingBrand = offerRepository.findById(uuid);
                if (existingBrand == null)
                    throw new EntityNotFoundException("Offer not found!");
                Offer offer = existingBrand.get();
                modelMapper.map(newOfferDTO, offer);
                offerRepository.saveAndFlush(offer);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        offerRepository.deleteById(uuid);
    }

    @Override
    public Optional<Offer> findByUuid(String uuid) {
        return offerRepository.findById(uuid);
    }

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }
}
