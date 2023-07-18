// UserService.java
package com.example.jsplogin.service;


import com.example.jsplogin.models.User;
import com.example.jsplogin.repogitory.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //인증
    public boolean authenticate(String id, String password) {
        User user = userRepository.getUserById(id);
        return user != null && user.getPassword().equals(password);
    }

    //추가
    public void registerUser(User user) {
        userRepository.addUser(user);
    }
}
