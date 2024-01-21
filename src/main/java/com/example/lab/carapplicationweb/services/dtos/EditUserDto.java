package com.example.lab.carapplicationweb.services.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class EditUserDto {
    //    @UniqueUsername
    private String username;

    private String firstName;

    private String lastName;
    //    @UniqueEmail
    private String email;

    private boolean isActive;

    private String imageUrl;

    private String password;

    private String confirmPassword;

    //    @NotNull
//    @NotEmpty
//    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
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


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    //    @NotNull
//    @NotEmpty
//    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    //    @NotNull
//    @NotEmpty
//    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}