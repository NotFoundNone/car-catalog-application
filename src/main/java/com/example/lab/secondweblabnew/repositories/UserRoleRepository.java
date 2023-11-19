package com.example.lab.secondweblabnew.repositories;

import com.example.lab.secondweblabnew.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, String> {
}
