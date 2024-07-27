package com.rf.auth_server.repository;

import com.rf.auth_server.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
    Optional<Token> findByToken(String token);
}
