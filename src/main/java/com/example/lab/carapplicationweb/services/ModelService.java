package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Model;
import com.example.lab.carapplicationweb.services.dtos.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {

    List<ShowModelInfoDto> getAll();

    Optional<Model> findByUuid(UUID uuid);

    Optional<EditModelDto> findByName(String modelName);

    ShowDetailedModelInfoDto modelDetails(String modelName);

    void add(AddModelDto modelDTO);

    void update(EditModelDto newModelDTO);

    void deleteByUuid(UUID uuid);

    void deleteByName(String modelName);

    void deleteByFullName(String fullName);

}
