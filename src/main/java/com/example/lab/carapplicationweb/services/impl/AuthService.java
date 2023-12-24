package com.example.lab.carapplicationweb.services.impl;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.repositories.UserRoleRepository;
import com.example.lab.carapplicationweb.services.dtos.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
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
}