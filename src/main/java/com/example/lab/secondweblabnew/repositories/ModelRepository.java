package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String>
{
    Optional<Model> findByName(String name);
}
