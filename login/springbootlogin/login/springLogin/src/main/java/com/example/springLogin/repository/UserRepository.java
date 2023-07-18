package com.example.springLogin.repository;

import com.example.springLogin.model.User;

public interface UserRepository {

    void addUser(User user);

    User getUserById(String id);
}