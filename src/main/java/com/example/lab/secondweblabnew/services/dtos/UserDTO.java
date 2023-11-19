package com.example.lab.secondweblabnew.services.dtos;

import com.example.lab.secondweblabnew.models.Offer;
import com.example.lab.secondweblabnew.models.UserRole;

import java.util.Set;

public class UserDTO {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private String imageURL;

    private UserRoleDTO role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String firstName, String lastName, boolean isActive, String imageURL, UserRoleDTO role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageURL = imageURL;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }
}
