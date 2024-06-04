package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class java11GetHeaderTestRefactored {
    private static final String BASE_URL = "https://api.github.com/";
    static HttpClient httpClient = HttpClient.newBuilder().build();
    static HttpResponse<Void> response;

    @BeforeAll
    static void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        //Act - send request
        response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
    }


    @Test
    void getReturn200() {
        //Act
        int actualCode = response.statusCode();

        //Assert
        Assertions.assertEquals(200, actualCode);
    }

    @Test
    void contentTypeIsJson(){
        //Act
        String contentType = response.headers().firstValue("content-type").get();

        Assertions.assertEquals("application/json; charset=utf-8", contentType);
    }

    @Test
    void xRateLimitIsPresent() {
        String xRateLimit = response.headers().firstValue("X-Ratelimit-Limit").get();

        Assertions.assertEquals(60, Integer.parseInt(xRateLimit));
    }

    @ParameterizedTest
    @CsvSource({
            "X-Ratelimit-Limit,60",
            "content-type,application/json; charset=utf-8",
            "server,GitHub.com",
            "x-frame-options,deny"
    })
    void parametrizedTestForHeaders(String header, String expectedValue) {
        String contentType = response.headers().firstValue(header).get();

        Assertions.assertEquals(expectedValue, contentType);
    }
}
