package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.apirequestexception.*;
import com.leffycruze.doodle.security.DoodleUserDetailsService;
import com.leffycruze.doodle.security.jwt.JwtProvider;
import com.leffycruze.doodle.security.jwt.Token;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoodleUserDetailsService userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, Object> request)
            throws BadRequestException {
        if (!request.containsKey("username") || !request.containsKey("password")) {
            throw new BadRequestException("Username and password must be provided");
        }

        String username = (String) request.get("username");
        String password = (String) request.get("password");

        return new ResponseEntity<>(userService.register(username, password), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") String id) throws NotFoundException, BadRequestException {
        try {
            Integer idInt = Integer.parseInt(id);
            Optional<User> u = userService.findById(idInt);
            if (u.isEmpty())
                throw new NotFoundException("User=" + id + " not found");

            return u.get();

        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid id");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(HttpServletRequest httpRequest) {
        String username = (String) httpRequest.getAttribute("username");
        User user = userService.findByUsername(username);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> request)
            throws NotFoundException, ForbiddenException {
        String username = (String) request.get("username");
        String password = (String) request.get("password");

        User user = userService.findByLoginAndPassword(username, password);
        String token = jwtProvider.generateToken(user.getUsername());

        return ResponseEntity.ok(new Token(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        try {
            Integer idInt = Integer.parseInt(id);
            Optional<User> u = userService.findById(idInt);
            if (u.isEmpty())
                throw new NotFoundException("User=" + id + " not found");

            userService.delete(u.get());

            return ResponseEntity.noContent().build();

        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid id");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
