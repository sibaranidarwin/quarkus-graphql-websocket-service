package org.acme.resource;

import org.acme.entity.Author;
import org.acme.entity.Book;
import org.acme.service.BookService;
import org.eclipse.microprofile.graphql.*;
import jakarta.inject.Inject;
import java.util.List;


@GraphQLApi
public class BookGraphql {

    @Inject
    BookService bookService;

    @Query("allBooks")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @Query("allAuthor")
    public List<Author> getAllAuthor() {
        return bookService.getAuthors();
    }

    @Query("allAuthorN1Problem")
    public List<Author> getAllAuthorN(){
        return bookService.getAuthorsNProblems();
    }

    @Query("book")
    public List<Book> getBook(Long id){
        return bookService.getBook(id);
    }

    @Mutation
    public Book addBook(String title, int year, Long authorId) {
        return bookService.addBook(title, year, authorId);
    }
}
