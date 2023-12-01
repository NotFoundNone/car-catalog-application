package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.Brand;
import com.example.lab.secondweblabnew.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
    Optional<User> findByUsername(String username);
}
