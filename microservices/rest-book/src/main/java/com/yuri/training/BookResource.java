package com.yuri.training;

import com.yuri.training.model.Book;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {

    @Inject
    Logger logger;

    @RestClient
    @Inject
    NumberProxy proxy;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
      summary="Creates a book",
      description="Creates a book with an ISBN number"
    )
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")
    @Retry(maxRetries = 3, delay = 3000)
    public Response createBook(
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("year") int yearOfPublication,
            @FormParam("genre") String genre) {
        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();

        logger.info("Book created: " + book.title);
        return Response.status(201).entity(book).build();
    }

    public Response fallbackOnCreatingABook(
            String title,
            String author,
            int yearOfPublication,
            String genre) {
        Book book = new Book();
        book.isbn13 = "Will be created later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        logger.warn("Saving book on disk: " + book.title);
        saveBookOnDisk(book);
        return Response.status(206).entity(book).build();
    }

    private void saveBookOnDisk(Book book) {
        String bookJson = JsonbBuilder.create().toJson(book);
        try {
            String fileName = "book-"+ Instant.now().toEpochMilli()+ ".json";
            PrintWriter out = new PrintWriter(fileName);
            out.write(bookJson);
            logger.warn("Book saved on disk with name: " + fileName);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
