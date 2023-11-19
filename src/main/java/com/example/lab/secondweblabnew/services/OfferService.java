package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.services.dtos.OfferDTO;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void add(OfferDTO offerDTO);

    void update(String uuid, OfferDTO newOfferDTO);

    void deleteByUuid(String uuid);

    Optional<Offer> findByUuid(String uuid);

    List<Offer> getAll();
}
