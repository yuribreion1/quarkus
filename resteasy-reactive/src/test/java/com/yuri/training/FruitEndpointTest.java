package com.yuri.training;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class FruitEndpointTest {

    @Test
    void testListAllFruits() {
        Response response = given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
        assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("Cherry","Apple","Banana");

        given()
                .when()
                .body("{\"name\" : \"Pineapple\"}")
                .contentType("application/json")
                .put("/fruits/1")
                .then()
                .statusCode(200)
                .body(
                        containsString("\"id\":"),
                        containsString("\"name\":\"Pineapple\""));

        response = given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
        assertThat(response.jsonPath().getList("name"))
                .containsExactlyInAnyOrder("Pineapple","Apple","Banana");

    }
}
