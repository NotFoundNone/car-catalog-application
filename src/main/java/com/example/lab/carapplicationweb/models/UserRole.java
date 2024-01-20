package com.example.lab.carapplicationweb.models;

import com.example.lab.carapplicationweb.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    private Role role;

    public UserRole() {
    }

    public UserRole(Role role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.toString();
    }
}
