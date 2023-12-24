package com.example.lab.carapplicationweb.services;

import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.services.dtos.AddUserDto;
import com.example.lab.carapplicationweb.services.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(AddUserDto userDTO);

    void update(String uuid, UserDTO newUserDTO);

    void deleteByUuid(String uuid);

    Optional<User> findByUuid(String uuid);

    public boolean isUserAdmin(String username);

    List<User> getAll();

    public User findByUsername(String userName);

}
