package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.apirequestexception.AuthenticationFailureException;
import com.leffycruze.doodle.exception.apirequestexception.BadParameterException;
import com.leffycruze.doodle.exception.apirequestexception.MissingParametersException;
import com.leffycruze.doodle.exception.apirequestexception.UserNotFoundException;
import com.leffycruze.doodle.exception.apirequestexception.UsernameAlreadyTakenException;
import com.leffycruze.doodle.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, Object> request) throws UsernameAlreadyTakenException, MissingParametersException {
        // TODO hash password

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
    public User login(@RequestBody Map<String, Object> request) throws UserNotFoundException, AuthenticationFailureException {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        User u = userService.findByUsername(username);

        if (u.getPassword().equals(password)) { // TODO hash password
            u.setToken(getJWTToken(u));
            userService.save(u);
            return u;
        }

        throw new AuthenticationFailureException("Username or password incorrect");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
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

    private String getJWTToken(User user) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts
                .builder()
                .setId("leffycruze.doodle")
                .setSubject(String.valueOf(user.getId()))
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.MAX_VALUE)) //600000
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
    }
}
