package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.services.dtos.AddOfferDto;
import com.example.lab.carapplicationweb.services.dtos.OfferDTO;
import com.example.lab.carapplicationweb.services.dtos.ShowDetailedOfferInfoDto;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void add(AddOfferDto offerDTO);

    void update(String uuid, OfferDTO newOfferDTO);

    ShowDetailedOfferInfoDto offerDetails(String offerUuid);

    void deleteByFullName(String fullName);

    List<Offer> getTop3MostExpensiveOffers();

    void deleteByUuid(String uuid);

    Optional<Offer> findByUuid(String uuid);

    List<Offer> getAll();
}
