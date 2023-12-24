package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.Offer;
import com.example.lab.carapplicationweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository <Offer, String> {
    List<Offer> findAllBySeller(User seller);
    @Modifying
    @Transactional
    @Query("DELETE FROM Offer AS e WHERE CONCAT(e.model.name, ' ', e.seller.username, ' ', e.year, ' ', e.price, ' ', e.mileage) = :fullName")
    void deleteOfferByFullName(String fullName);
}
