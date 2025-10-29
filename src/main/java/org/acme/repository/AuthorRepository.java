package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Author;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {

//    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
//    List<Author> findAllWithBooks();

    public List<Author> findAllWithBooks(){
        return find("SELECT a FROM Author a LEFT JOIN FETCH a.books").list();
    }

}
