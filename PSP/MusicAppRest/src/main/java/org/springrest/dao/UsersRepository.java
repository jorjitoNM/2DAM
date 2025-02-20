package org.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springrest.domain.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User getUserByCode(String code);
    User getUserByEmail(String email);
}
