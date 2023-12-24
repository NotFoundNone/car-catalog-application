package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.models.UserRole;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.repositories.UserRoleRepository;
import com.example.lab.carapplicationweb.services.UserService;
import com.example.lab.carapplicationweb.services.dtos.AddUserDto;
import com.example.lab.carapplicationweb.services.dtos.EditUser;
import com.example.lab.carapplicationweb.services.dtos.UserDTO;
import com.example.lab.carapplicationweb.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
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
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void add(AddUserDto newUser) {
        if (!validationUtil.isValid(newUser))
        {
            this.validationUtil
                    .violations(newUser)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                User user = modelMapper.map(newUser, User.class);
                user.setRole((List<UserRole>) userRoleRepository.findUserRoleByRole(newUser.getRole()).orElse(null));
                this.userRepository.saveAndFlush(user);
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    public boolean isUserAdmin(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getRole().stream().anyMatch(role -> role.getRole() == Role.ADMIN);
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

    @Override
    public User findByUsername(String userName) {
        return this.userRepository.findUserByUsername(userName).orElse(null);
    }

    @CacheEvict(value = "users", allEntries = true)
    public EditUser update(EditUser editUser) {
        User user = findByUsername(editUser.getUsername());
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        return modelMapper.map(userRepository.save(user), EditUser.class);
    }

}
