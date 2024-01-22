package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository <Brand, UUID> {

    Optional<Brand> findByUuid(UUID uuid);

    Optional<Brand> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
