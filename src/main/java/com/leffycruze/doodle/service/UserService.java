package com.leffycruze.doodle.service;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.UserNotFoundException;
import com.leffycruze.doodle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public User register(Map<String, Object> request){
        String username = (String) request.get("username");
        String password = (String) request.get("password");

        User u = new User(username, password);
        return repository.save(u);
    }

    public Optional<User> findById(Integer id){
        return repository.findById(id);
    }

    public User findByUsername(String username) throws UserNotFoundException {
        Optional<User> u = repository.findByUsername(username);

        if (u.isEmpty()){
            throw new UserNotFoundException();
        }
        return repository.getOne(u.get().getId());
    }

    public void save(User user){
        repository.saveAndFlush(user);
    }
}
