package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11PostFails {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void postWithoutAutorizationFails() throws IOException, InterruptedException {
        //Arrange - create client
        HttpClient httpClient = HttpClient.newBuilder().build();

        //Arrange - create request
        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL + "user/repos"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        //Act - send request
        HttpResponse<Void> response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        //Assert
        Assertions.assertEquals(401, actualCode);

    }
}
