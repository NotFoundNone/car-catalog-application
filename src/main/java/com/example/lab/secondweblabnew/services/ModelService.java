package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.Model;
import com.example.lab.secondweblabnew.services.dtos.ModelDTO;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    void add(ModelDTO modelDTO);

    void update(String uuid, ModelDTO newModelDTO);

    void deleteByUuid(String uuid);

    Optional<Model> findByUuid(String uuid);

    List<Model> getAll();

}
