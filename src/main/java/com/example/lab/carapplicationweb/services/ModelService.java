package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.Model;
import com.example.lab.carapplicationweb.services.dtos.AddModelDto;
import com.example.lab.carapplicationweb.services.dtos.ModelDTO;
import com.example.lab.carapplicationweb.services.dtos.ShowDetailedModelInfoDto;
import com.example.lab.carapplicationweb.services.dtos.ShowModelInfoDto;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    void add(AddModelDto modelDTO);

    void update(String uuid, ModelDTO newModelDTO);

    ShowDetailedModelInfoDto modelDetails(String modelName);

    void deleteByUuid(String uuid);

    void deleteByFullName(String fullName);

    Optional<Model> findByUuid(String uuid);

    List<ShowModelInfoDto> getAll();

    void deleteByName(String modelName);
}
