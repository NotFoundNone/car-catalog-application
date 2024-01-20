package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.dtos.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OfferService {

    void add(AddOfferDto offerDTO);

    void update(EditOfferDto newOfferDTO);

    ShowDetailedOfferInfoDto offerDetails(String offerUuid);

    void deleteByFullName(String fullName);

    List<Offer> getTop3MostExpensiveOffers();

    void deleteByUuid(String uuid);

    Optional<Offer> findByUuid(String uuid);

    Optional<EditOfferDto> findEditOfferDtoByUuid(String uuid);

    List<ShowOfferInfoDto> getAll();

    public List<ShowOfferInfoDto> getAllBySeller(String username);
}
