package com.example.lab.carapplicationweb.init;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.User;
import com.example.lab.carapplicationweb.models.UserRole;
import com.example.lab.carapplicationweb.repositories.UserRepository;
import com.example.lab.carapplicationweb.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;


    public DataInitializer(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,@Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new UserRole(Role.ADMIN);
            var normalUserRole = new UserRole(Role.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initNormalUser();
        }
    }
//
    private void initAdmin(){
        var adminRole = userRoleRepository.
                findUserRoleByRole(Role.ADMIN).orElseThrow();

        var adminUser = new User("admin","Nikita", "Dema", "admin@example.com", passwordEncoder.encode(defaultPassword));
        adminUser.setRole(List.of(adminRole));

        userRepository.save(adminUser);
    }

    private void initNormalUser(){
        var userRole = userRoleRepository.
                findUserRoleByRole(Role.USER).orElseThrow();

        var normalUser = new User("user", passwordEncoder.encode(defaultPassword), "user@example.com", "User Userovich", "22");
        normalUser.setRole(List.of(userRole));

        userRepository.save(normalUser);
    }
}

