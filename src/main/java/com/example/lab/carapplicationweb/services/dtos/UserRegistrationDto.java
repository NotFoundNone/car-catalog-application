package com.example.lab.carapplicationweb.services.dtos;

import jakarta.validation.constraints.*;

public class UserRegistrationDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;

    public UserRegistrationDto() {}

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Firstname cannot be null or empty!")
    @Size(min = 2, max = 40)
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    @NotEmpty(message = "Lastname cannot be null or empty!")
    @Size(min = 2, max = 40)
    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    @NotEmpty(message = "Email cannot be null or empty!")
    @Size(min = 5, max = 40)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
