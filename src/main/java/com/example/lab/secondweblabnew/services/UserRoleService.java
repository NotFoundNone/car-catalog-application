package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.UserRole;
import com.example.lab.secondweblabnew.services.dtos.UserRoleDTO;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    void add(UserRoleDTO userRoleDTO);

    void update(String uuid, UserRoleDTO newUserRoleDTO);

    void deleteByUuid(String uuid);

    Optional<UserRole> findByUuid(String uuid);

    List<UserRole> getAll();
}
