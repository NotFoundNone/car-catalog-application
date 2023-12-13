package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.models.UserRole;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.AddUserDto;
import com.example.lab.carapplicationweb.services.dtos.UserDTO;
import com.example.lab.carapplicationweb.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRepository (UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public void add(AddUserDto userDTO) {
        if (!validationUtil.isValid(userDTO))
        {
            this.validationUtil
                    .violations(userDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                UserRole user = new UserRole();
                user.setRole(Role.USER);
                userDTO.setRole(user);
                this.userRepository.saveAndFlush(this.modelMapper.map(userDTO, User.class));
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void update(String uuid, UserDTO newUserDTO) {
        if (!validationUtil.isValid(newUserDTO))
        {
            this.validationUtil
                    .violations(newUserDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Optional<User> existingUser = userRepository.findById(uuid);
                if (existingUser == null)
                    throw new EntityNotFoundException("User not found!");
                User user = existingUser.get();
                modelMapper.map(newUserDTO, user);
                userRepository.saveAndFlush(user);
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public Optional<User> findByUuid(String uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
