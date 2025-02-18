package org.springrest.domain.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@JsonIncludeProperties({"email"})
public class User {
    @Id
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String code;
    @Column
    private boolean active;

    public User(String email, String password, String randomCode, boolean b) {
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
