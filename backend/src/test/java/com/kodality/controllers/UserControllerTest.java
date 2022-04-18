package com.kodality.controllers;

import com.kodality.dtos.User;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UserControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void test_to_get_all_users() {

        HttpRequest<List<User>> request = HttpRequest.GET("/api/users");
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        assertEquals(2, response.body().stream().count());
    }
}