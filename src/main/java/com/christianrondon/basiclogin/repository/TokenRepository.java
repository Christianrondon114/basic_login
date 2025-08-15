package com.christianrondon.basiclogin.repository;

import com.christianrondon.basiclogin.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
}
