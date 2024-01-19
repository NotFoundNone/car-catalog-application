package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Model;
import com.example.lab.carapplicationweb.services.dtos.*;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    void add(AddModelDto modelDTO);

    void update(EditModelDto newModelDTO);

    ShowDetailedModelInfoDto modelDetails(String modelName);

    void deleteByUuid(String uuid);

    void deleteByFullName(String fullName);

    Optional<Model> findByUuid(String uuid);

    Optional<EditModelDto> findByName(String modelName);

    List<ShowModelInfoDto> getAll();

    void deleteByName(String modelName);
}
