package com.authorizationsystem.service;

import com.authorizationsystem.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    void update(User user);

    void remove(User user);

    User findByLogin(String login);

    User findById(Long id);

    List<User> findAll();

    User findByEmail(String email);

    boolean checkBirthday(User user);
}
