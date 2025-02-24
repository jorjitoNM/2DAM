package org.springrest.domain.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@JsonIncludeProperties({"email"})
@Builder
@AllArgsConstructor
public class User {
    @Id
    @Column
    @JsonValue
    private String email;
    @Column
    private String password;
    @Column
    private String code;
    @Column
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private RolesEntity roles;

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
}
