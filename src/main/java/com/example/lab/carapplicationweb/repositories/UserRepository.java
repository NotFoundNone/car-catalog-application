package com.example.lab.carapplicationweb.repositories;


import com.example.lab.carapplicationweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

//    User findUserByName(String name);

//    @Modifying
//    @Transactional
//    void deleteByUsername(String username);
}
