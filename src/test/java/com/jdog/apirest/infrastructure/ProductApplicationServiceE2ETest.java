package com.jdog.apirest.infrastructure;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApplicationServiceE2ETest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void whenGetSortedProducts_thenStatus200() {
        given().
                queryParam("salesWeight", 0.5).
                queryParam("stockWeight", 0.3).
                when().
                get("/products/sort").
                then().
                assertThat().
                statusCode(HttpStatus.OK.value()).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body("size()", greaterThan(0));
    }

    @Test
    public void whenGetSortedProductsWithNegativeWeight_thenStatus400() {
        given().
                queryParam("salesWeight", -0.1).
                queryParam("stockWeight", -0.2).
                when().
                get("/products/sort").
                then().
                assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
