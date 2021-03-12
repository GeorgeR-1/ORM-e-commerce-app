package com.cybertek.service;

import com.cybertek.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws Exception;

    List<User> readAll();

    User readByUsername(String username);

    User readByEmail(String email);

    void update(User user);

    void deactivateAccount(Long id) throws Exception;

    User confirmUser(User user);



}
