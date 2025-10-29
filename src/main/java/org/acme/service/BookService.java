package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.Author;
import org.acme.entity.Book;
import org.acme.exception.DataNotFoundException;
import org.acme.repository.AuthorRepository;
import org.acme.resource.BookWebSocket;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    BookWebSocket ws;

    @Inject
    AuthorRepository authorRepository;

    public List<Book> getBooks() {
        try {
            List<Book> books = Book.listAll();

            for (int i = 0; i < books.size(); i++) {
                for (int j = 0; j < books.size(); j++) {
                    books.get(i).title.length();
                }
            }
            return books;
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e);
        }
    }

    public List<Author> getAuthors() {
        try {
            // Solved n + 1 problems
            List<Author> authors1 = authorRepository.findAllWithBooks();
            return authors1;
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }


    public List<Author> getAuthorsNProblems() {
        try {
            //    n + 1 problems
            List<Author> authors = Author.listAll();
            for (Author author : authors) {
                author.books.size();
            }
            return authors;
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }


    public List<Book> getBook(Long id) {
        try {
            List<Book> findBook = Book.find("id", id).list();
            return findBook;
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }

    @Transactional
    public Book addBook(String title, int year, Long authorId) {
        try {
            Author author = Author.findById(authorId);
            if (author == null) return null;

            Book book = new Book();
            book.title = title;
            book.year = year;
            book.author = author;
            book.persist();

            if (book != null) {
                ws.darwinBroadcast("New book added: " + book.title);
            }

            return book;
        } catch (Exception e) {
            throw new DataNotFoundException(e);
        }
    }
}

