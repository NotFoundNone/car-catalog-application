package com.example.lab.secondweblabnew.services.dtos;

import com.example.lab.secondweblabnew.models.UserRole;
import com.example.lab.secondweblabnew.util.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddUserDto {

    private String firstName;

    private String lastName;
    @UniqueUsername
    private String username;

    private String password;

    private UserRole role;

    @NotNull(message = "First name cannot be null or empty!")
    @Size(min = 2, message = "First name should be at least 2 characters long!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull(message = "Last name cannot be null or empty!")
    @Size(min = 2, message = "Last name should be at least 2 characters long!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Username cannot be null or empty!")
    @Size(min = 2, message = "Username should be at least 2 characters long!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password cannot be null or empty!")
    @Size(min = 4, message = "Password should be at least 4 characters long!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
