package org.primerservidorspring.domain.model;

public record User(
        String username,
        String password,
        int id,
        String code,
        boolean active
) {
}
