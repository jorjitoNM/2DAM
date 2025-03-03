package org.springrest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String rol;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
