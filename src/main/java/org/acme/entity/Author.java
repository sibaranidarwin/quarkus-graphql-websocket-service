package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Author extends PanacheEntity {
    public String name;

    @OneToMany(mappedBy = "author")
    public List<Book> books;
}
