package com.yuri.training;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class BookResourceTest {

    @Test
    @DisplayName("TEST - Create a new book")
    public void shouldCreateABook() {
        given()
            .formParam("title", "Understanding Quarkus")
            .formParam("author", "Antonio Goncalves")
            .formParam("year", 2020)
            .formParam("genre", "IT")
        .when()
                .post("/api/books")
                .then()
                .statusCode(201)
                .body("isbn_13", startsWith("13-"))
                .body("title", is("Understanding Quarkus"))
                .body("author", is("Antonio Goncalves"))
                .body("year_of_publication", is(2020))
                .body("genre", is("IT"))
                .body("creation_date", startsWith("20"));

    }

}