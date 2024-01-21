package com.example.lab.carapplicationweb.repositories;


import com.example.lab.carapplicationweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findByEmail(String email);
}
