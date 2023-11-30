package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository <Brand, String> {
    Optional<Brand> findByName(String name);
}
