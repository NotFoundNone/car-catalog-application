package com.example.lab.carapplicationweb.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseCreatedEntity{

    String name;

    Set<Model> model;

    public Brand() {
        model = new HashSet<>();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", cascade = CascadeType.REMOVE)
    public Set<Model> getModel() {
        return model;
    }

    public void setModel(Set<Model> model) {
        this.model = model;
    }

    public Brand(LocalDate created,
                 LocalDate modified,
                 String name) {
        super(created, modified);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
