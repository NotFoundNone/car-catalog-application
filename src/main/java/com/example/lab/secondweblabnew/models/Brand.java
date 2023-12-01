package com.example.lab.secondweblabnew.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseCreatedEntity{

    String name;

    Set<Model> model;

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

    protected Brand(){

    }
}
