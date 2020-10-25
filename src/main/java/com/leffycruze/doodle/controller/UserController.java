package com.leffycruze.doodle.controller;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.UserNotFoundException;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User register(@RequestBody Map<String, Object> request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public User getUser(@PathVariable(name = "id") Integer id) throws UserNotFoundException {
        Optional<User> u = userService.findById(id);
        if (u.isEmpty())
            throw new UserNotFoundException();

        return u.get();
    }
}
