package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OfferRepository extends JpaRepository <Offer, String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Offer AS e WHERE CONCAT(e.model.name, ' ', e.seller.username, ' ', e.year, ' ', e.price, ' ', e.mileage) = :fullName")
    void deleteOfferByFullName(String fullName);
}
