package com.yurib.training;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.apache.http.entity.ContentType.TEXT_PLAIN;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldGetAllBooks() {
        given()
            .header(ACCEPT, APPLICATION_JSON)
      .when()
            .get("/api/books")
      .then()
         .statusCode(200).body("size()", is(3));
    }

    @Test
    public void shouldCountAllBooks() {
        given()
            .header(ACCEPT, TEXT_PLAIN)
        .when().get("/api/books/count")
            .then()
                .statusCode(200)
                .body(is(3));
    }

}