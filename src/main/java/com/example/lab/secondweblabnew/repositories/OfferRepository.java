package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository <Offer, String> {
}
