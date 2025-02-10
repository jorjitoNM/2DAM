package org.springrest2.domain.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String code;
    private boolean active;

    public User(int i, String email, String password, String randomCode, boolean b) {
        this.id = i;
        this.email = email;
        this.password = password;
        this.code = randomCode;
        this.active = b;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String code) {
        this.email = email;
        this.password = password;
        this.code = code;
    }
}
