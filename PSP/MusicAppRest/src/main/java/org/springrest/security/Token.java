package org.springrest.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Token {
    private String login;
    private String refresh;
}
