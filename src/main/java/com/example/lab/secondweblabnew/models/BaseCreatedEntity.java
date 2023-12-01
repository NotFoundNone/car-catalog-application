package com.example.lab.secondweblabnew.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseCreatedEntity extends BaseEntity {

    private LocalDate created;

    private LocalDate modified;

    @Column(name = "created", columnDefinition = "DATE")
    @CreatedDate
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "modified", columnDefinition = "DATE")
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
