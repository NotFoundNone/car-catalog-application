package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
