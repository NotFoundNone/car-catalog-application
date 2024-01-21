package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.dtos.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OfferService {

    List<ShowOfferInfoDto> getAll();

    Optional<Offer> findByUuid(String uuid);

    Optional<EditOfferDto> findEditOfferDtoByUuid(String uuid);

    ShowDetailedOfferInfoDto offerDetails(String offerUuid);

    List<Offer> getTop3MostExpensiveOffers();

    public List<ShowOfferInfoDto> getAllBySeller(String username);

    void add(AddOfferDto offerDTO);

    void update(EditOfferDto newOfferDTO);

    void deleteByFullName(String fullName);

    void deleteByUuid(String uuid);

}
