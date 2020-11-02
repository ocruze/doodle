package com.leffycruze.doodle.controller;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.UserNotFoundException;
import com.leffycruze.doodle.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User register(@RequestBody Map<String, Object> request) {
        // TODO hash password
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

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public User login(@RequestBody Map<String, Object> request) throws UserNotFoundException {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        User u = userService.findByUsername(username);

        if(u.getPassword().equals(password)){ // TODO hash password
            u.setToken(getJWTToken(username));
            userService.save(u);
            return u;
        }

        return null;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("leffycruze.doodle")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
