// UserRepository.java
package com.example.jsplogin.repogitory;

import com.example.jsplogin.models.User;

public interface UserRepository {

    void addUser(User user);

    User getUserById(String id);
}
