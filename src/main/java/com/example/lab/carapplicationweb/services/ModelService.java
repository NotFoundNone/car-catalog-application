package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Model;
import com.example.lab.carapplicationweb.services.dtos.*;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    List<ShowModelInfoDto> getAll();

    Optional<Model> findByUuid(String uuid);

    Optional<EditModelDto> findByName(String modelName);

    ShowDetailedModelInfoDto modelDetails(String modelName);

    void add(AddModelDto modelDTO);

    void update(EditModelDto newModelDTO);

    void deleteByUuid(String uuid);

    void deleteByName(String modelName);

    void deleteByFullName(String fullName);

}
