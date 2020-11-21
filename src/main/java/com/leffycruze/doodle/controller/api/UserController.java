package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.security.DoodleUserDetailsService;
import com.leffycruze.doodle.security.jwt.JwtProvider;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.apirequestexception.AuthenticationFailureException;
import com.leffycruze.doodle.exception.apirequestexception.BadParameterException;
import com.leffycruze.doodle.exception.apirequestexception.MissingParametersException;
import com.leffycruze.doodle.exception.apirequestexception.UserNotFoundException;
import com.leffycruze.doodle.exception.apirequestexception.UsernameAlreadyTakenException;
import com.leffycruze.doodle.security.jwt.Token;
import com.leffycruze.doodle.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<User> register(@RequestBody Map<String, Object> request) throws UsernameAlreadyTakenException, MissingParametersException {
        if (!request.containsKey("username") || !request.containsKey("password")) {
            throw new MissingParametersException("Username and password must be provided");
        }

        String username = (String) request.get("username");
        String password = (String) request.get("password");

        return new ResponseEntity<>(userService.register(username, password), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") String id) throws UserNotFoundException, BadParameterException {
        try {
            Integer idInt = Integer.parseInt(id);
            Optional<User> u = userService.findById(idInt);
            if (u.isEmpty())
                throw new UserNotFoundException("User=" + id + " not found");

            return u.get();

        } catch (NumberFormatException e) {
            throw new BadParameterException("Invalid id");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> request) throws UserNotFoundException, AuthenticationFailureException {
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
                throw new UserNotFoundException("User=" + id + " not found");

            userService.delete(u.get());

            return ResponseEntity.noContent().build();

        } catch (NumberFormatException e) {
            throw new BadParameterException("Invalid id");
        }
    }
}
