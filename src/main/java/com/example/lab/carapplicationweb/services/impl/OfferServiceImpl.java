package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.repositories.ModelRepository;
import com.example.lab.carapplicationweb.repositories.OfferRepository;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.services.OfferService;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.*;
import com.example.lab.carapplicationweb.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;
    private UserService userService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferRepository (OfferRepository offerRepository) { this.offerRepository = offerRepository;}

    @Autowired
    public void setModelRepository (ModelRepository modelRepository) { this.modelRepository = modelRepository;}

    @Autowired
    public void setUserRepository (UserRepository userRepository) { this.userRepository = userRepository;}

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void add(AddOfferDto offerDTO) {
        if (!validationUtil.isValid(offerDTO))
        {
            this.validationUtil
                    .violations(offerDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
//                Offer offer = modelMapper.map(offerDTO, Offer.class);
//                String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//                if (userService.isUserAdmin(currentUsername)) {
//                    offer.setSeller(userRepository.findByUsername(offerDTO.getSellerUsername()).orElse(null));
//                }
//                else{
//                    offer.setSeller(userRepository.findByUsername(currentUsername).orElse(null));
//                }
//                offer.setModel(modelRepository.findByName(offerDTO.getModelName()).orElse(null));
//                this.offerRepository.saveAndFlush(offer);
                Offer offer = modelMapper.map(offerDTO, Offer.class);
                offer.setModel(modelRepository.findByName(offerDTO.getModelName()).orElse(null));
                offer.setSeller(userRepository.findByUsername(offerDTO.getSellerUsername()).orElse(null));
                this.offerRepository.saveAndFlush(offer);
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
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
    public ShowDetailedOfferInfoDto offerDetails(String offerUuid)
    {
        return modelMapper.map(offerRepository.findById(offerUuid).orElse(null), ShowDetailedOfferInfoDto.class);
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void deleteByFullName(String fullName)
    {
        offerRepository.deleteOfferByFullName(fullName);
    }

    @Override
    public List<Offer> getTop3MostExpensiveOffers() {
        List<Offer> allOffers = offerRepository.findAll();

        List<Offer> top3MostExpensiveOffers = allOffers.stream()
                .sorted(Comparator.comparingInt(Offer::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return top3MostExpensiveOffers;
    }

    @Override
    @Cacheable("offers")
    public List<ShowOfferInfoDto> getAllBySeller(String sellerUsername) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User seller = userRepository.findByUsername(sellerUsername)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Offer> offers = offerRepository.findAllBySeller(seller);
        return offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = {"offers"},  allEntries = true)
    public void deleteByUuid(String uuid) {
        offerRepository.deleteById(uuid);
    }

    @Override
    public Optional<Offer> findByUuid(String uuid) {
        return offerRepository.findById(uuid);
    }

    @Override
    @Cacheable("offers")
    public List<ShowOfferInfoDto> getAll() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }
}
