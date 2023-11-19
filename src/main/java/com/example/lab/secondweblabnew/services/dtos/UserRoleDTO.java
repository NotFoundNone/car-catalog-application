package com.example.lab.secondweblabnew.services.dtos;

import com.example.lab.secondweblabnew.enums.Role;

public class UserRoleDTO {
    private String uuid;

    private Role role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Role role) {
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Role getName() {
        return role;
    }

    public void setName(Role role) {
        this.role = role;
    }

}
