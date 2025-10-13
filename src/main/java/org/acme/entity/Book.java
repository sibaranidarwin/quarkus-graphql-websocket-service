package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Book extends PanacheEntity {
    public String title;
    public int year;

    @ManyToOne
    public Author author;
}

