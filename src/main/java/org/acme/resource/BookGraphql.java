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

    @Inject
    BookWebSocket ws;

    @Query("allBooks")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }


    @Query("allAuthor")
    public List<Author> getAllAuthor() {
        return bookService.getAuthors();
    }

    @Query("book")
    public List<Book> getBook(Long id){
        return bookService.getBook(id);
    }

    @Mutation
    public Book addBook(String title, int year, Long authorId) {
        Book book = bookService.addBook(title, year, authorId);
        if (book != null) {
            ws.broadcast("New book added: " + book.title);
        }
        return book;
    }
}
