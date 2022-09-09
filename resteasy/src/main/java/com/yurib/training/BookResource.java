package com.yurib.training;

import com.yurib.training.model.Book;
import com.yurib.training.repository.BookRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("api/books")
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        logger.info("Returns all books");
        return bookRepository.getAllBooks();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllBooks() {
        logger.info("Returns the number of books");
        return bookRepository.getAllBooks().size();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Book> getBookById(@PathParam("id") int id) {
        logger.info("Returns a single book based on the ID: " +  id);
        return bookRepository.getBookById(id);
    }
}
