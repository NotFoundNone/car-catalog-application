package com.example.lab.carapplicationweb.models;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    protected String uuid;

    public BaseEntity()
    {}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
