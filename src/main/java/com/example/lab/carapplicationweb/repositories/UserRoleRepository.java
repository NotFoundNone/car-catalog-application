package com.example.lab.carapplicationweb.repositories;

import com.example.lab.carapplicationweb.enums.Role;
import com.example.lab.carapplicationweb.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, String> {

    Optional<UserRole> findUserRoleByRole(String role);

    Optional<UserRole> findUserRoleByRole(Role role);
}
