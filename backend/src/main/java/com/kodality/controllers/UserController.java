package com.kodality.controllers;

import com.kodality.dtos.User;
import com.kodality.services.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import javax.inject.Inject;
import java.util.List;

@Controller("/api")
public class UserController {

    @Inject
    private UserService userService;

    @Get(value = "users")
    public HttpResponse<List<User>> getAllUsers() {
        return HttpResponse.ok().body(userService.getAllUsers());
    }
}
