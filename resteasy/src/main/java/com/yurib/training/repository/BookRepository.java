package com.yurib.training.repository;

import com.yurib.training.model.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    public List<Book> getAllBooks() {
        return List.of(
                new Book(1, "Understanding Quarkus", "Antonio", 2020, "IT"),
                new Book(2, "Practicing Quarkus", "Antonio", 2021, "IT"),
                new Book(3, "Effective Java", "Josh Bloch", 2001, "IT")
        );
    }

    public Optional<Book> getBookById(int id) {
        return getAllBooks().stream().filter(book -> book.id == id).findFirst();
    }
}
