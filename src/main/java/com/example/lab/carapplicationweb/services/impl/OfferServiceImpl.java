package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.repositories.ModelRepository;
import com.example.lab.carapplicationweb.repositories.OfferRepository;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.services.OfferService;
import com.example.lab.carapplicationweb.services.dtos.*;
import com.example.lab.carapplicationweb.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;
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

    @Override
    public Optional<Offer> findByUuid(UUID uuid) {
        return offerRepository.findById(uuid);
    }

    //не знаю пока что как по другому, нужно пересмотреть позже
    @Override
    public Optional<EditOfferDto> findEditOfferDtoByUuid(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findByUuid(uuid), EditOfferDto.class));
    }

    @Override
    @Cacheable("offers")
    public ShowDetailedOfferInfoDto offerDetails(UUID offerUuid)
    {
        return modelMapper.map(offerRepository.findById(offerUuid).orElse(null), ShowDetailedOfferInfoDto.class);
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

        User seller = userRepository.findUserByUsername(sellerUsername)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Offer> offers = offerRepository.findAllBySeller(seller);

        return offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }

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
                Offer offer = modelMapper.map(offerDTO, Offer.class);
                offer.setModel(modelRepository.findByName(offerDTO.getModelName()).orElse(null));
                offer.setSeller(userRepository.findUserByUsername(offerDTO.getSellerUsername()).orElse(null));
                this.offerRepository.saveAndFlush(offer);
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void update(EditOfferDto newOfferDTO) {
        if (!validationUtil.isValid(newOfferDTO)) {
            this.validationUtil
                    .violations(newOfferDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<Offer> existingOffer = offerRepository.findByUuid(newOfferDTO.getUuid());
                Offer offer = existingOffer.get();
                modelMapper.map(newOfferDTO, offer);
                offerRepository.saveAndFlush(offer);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void deleteByFullName(String fullName)
    {
        offerRepository.deleteOfferByFullName(fullName);
    }

    @Override
    @CacheEvict(cacheNames = "offers",  allEntries = true)
    public void deleteByUuid(UUID uuid) {
        offerRepository.deleteById(uuid);
    }


}
