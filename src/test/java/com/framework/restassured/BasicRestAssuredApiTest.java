package com.framework.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssuredApiTest {

    @Test
    void getStatusCodeIs200() {
        RestAssured.get("https://api.github.com")
                .then()
                .statusCode(200);
    }
    @Test
    void headersContainCorrectValue() {
        RestAssured.get("https://api.github.com")
                .then()
                .assertThat()
                .header("content-type", "application/json; charset=utf-8")
                .header("x-ratelimit-limit", "60");
    }

    @Test
    void bodyContainsCorrectValue() {
        RestAssured.get("https://api.github.com")
                .then()
                .assertThat()
                .body("login", equalTo("andrejs-ps"))
                .body("type", equalTo("User"));
    }
}
