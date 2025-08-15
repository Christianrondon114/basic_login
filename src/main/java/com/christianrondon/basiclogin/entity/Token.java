package com.christianrondon.basiclogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String value;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
