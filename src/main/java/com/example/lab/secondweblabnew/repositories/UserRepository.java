package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
}
