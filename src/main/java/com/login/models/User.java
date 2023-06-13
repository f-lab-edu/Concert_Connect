
package com.login.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
