package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    Optional<Credential> findByUserName(String username);
}
