package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, String> {
}
