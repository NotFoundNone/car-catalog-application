package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.services.dtos.AddOfferDto;
import com.example.lab.secondweblabnew.services.dtos.OfferDTO;
import com.example.lab.secondweblabnew.services.dtos.ShowDetailedOfferInfoDto;

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
