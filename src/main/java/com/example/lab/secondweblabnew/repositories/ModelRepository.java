package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String>
{
}
