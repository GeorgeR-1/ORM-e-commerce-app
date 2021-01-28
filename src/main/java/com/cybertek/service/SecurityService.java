package com.cybertek.service;

import com.cybertek.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SecurityService implements UserDetailsService {

    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = currentUser(s);

        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities(new ArrayList<>())
                .build();

    }

    private User currentUser(String s){

        return s.contains("@") ? userService.readByEmail(s) : userService.readByUsername(s);

    }
}
