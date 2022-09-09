package com.yuri.training;

import com.yuri.training.model.IsbnNumbers;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.Random;

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    @Inject
    Logger logger;

    @GET
    @Operation(
            summary = "Generate book numbers",
            description = "ISBN 10 and 13"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public IsbnNumbers generateNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers();
        isbnNumbers.isbn13 = "13-" + new Random().nextInt(100_000_000);
        isbnNumbers.isbn10 = "10-" + new Random().nextInt(100_000);
        isbnNumbers.generationDate = Instant.now();
        logger.info("NUMBERS - New generated ISBN: " + isbnNumbers);
        return isbnNumbers;
    }
}