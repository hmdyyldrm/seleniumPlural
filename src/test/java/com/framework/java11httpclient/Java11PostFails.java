package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.framework.java11httpclient.Token.TOKEN;

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
    @Test
    void postTest() throws IOException, InterruptedException {
        String jsonPayload = """
        {
            "name": "new-repo",
            "description": "This is your repository description",
            "private": false
        }
        """;
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL+"user/repos"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
        HttpResponse<String> response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println("response code***: " + response.statusCode());
        System.out.println("response body***: " + response.body());
    }

    @Test
    void patchTest() throws IOException, InterruptedException {
        String owner = "hmdyyldrm";
        String repo = "updated-repo2";
        String jsonPayload = """
        {
            "name": "updated-repo",
            "description": "This is the updated repository description",
            "private": false
        }
        """;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest patchRequest = HttpRequest.newBuilder(URI.create(BASE_URL + "repos/" + owner + "/" + repo))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(patchRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
