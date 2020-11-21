package com.leffycruze.doodle.security;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DoodleUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public DoodleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userService.findByUsername(username);
        return DoodleUserDetails.getUserDetailsByUserEntity(userEntity);
    }
}
