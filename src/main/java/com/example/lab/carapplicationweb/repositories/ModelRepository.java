package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String>
{
    Optional<Model> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Model AS e WHERE CONCAT(e.brand.name, ' ', e.name, ' ', e.startYear, ' ', e.endYear) = :fullName")
    void deleteModelByFullName(String fullName);
}
