package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.repositories.UserRoleRepository;
import com.example.lab.carapplicationweb.services.dtos.EditUser;
import com.example.lab.carapplicationweb.services.dtos.UserRegistrationDto;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegistrationDto registrationDTO) {
        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("Эта почта уже используется");
        }

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("Пароли должны совпадать");
        }

        var userRole = userRoleRepository.
                findUserRoleByRole(Role.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword())
        );

        user.setRole(List.of(userRole));

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    public boolean isUserAdmin(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getRole().stream().anyMatch(role -> role.getRole() == Role.ADMIN);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String userName) {
        return this.userRepository.findUserByUsername(userName).orElse(null);
    }

    public void deleteByUuid(String uuid) {
        userRepository.deleteById(uuid);
    }

    public Optional<User> findByUuid(String uuid) {
        return userRepository.findById(uuid);
    }

    @CacheEvict(value = "users", allEntries = true)
    public EditUser update(EditUser editUser) {
        User user = findByUsername(editUser.getUsername());
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        return modelMapper.map(userRepository.save(user), EditUser.class);
    }

}