package com.example.lab.secondweblabnew.services;

import com.example.lab.secondweblabnew.models.User;
import com.example.lab.secondweblabnew.services.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(UserDTO userDTO);

    void update(String uuid, UserDTO newUserDTO);

    void deleteByUuid(String uuid);

    Optional<User> findByUuid(String uuid);

    List<User> getAll();
}
