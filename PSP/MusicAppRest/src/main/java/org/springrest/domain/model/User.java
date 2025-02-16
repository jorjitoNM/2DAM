package org.springrest.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
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
