package com.example.lab.carapplicationweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseCreatedEntity extends BaseEntity {

    @Column(name = "modified", columnDefinition = "DATE")
    @LastModifiedDate
    private LocalDate modified;

    @Column(name = "created", columnDefinition = "DATE")
    @CreatedDate
    private LocalDate created;
}
