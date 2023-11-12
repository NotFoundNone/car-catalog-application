package com.example.lab.secondweblabnew.models;

import com.example.lab.secondweblabnew.enums.Role;
import jakarta.persistence.*;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    private Role role;

    private Set<User> user;

    public UserRole() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.REMOVE)
    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
