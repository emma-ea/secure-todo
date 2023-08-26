package com.emma_ea.secure_todo.token;

import com.emma_ea.secure_todo.model.UserAuthDetail;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EmailConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    @Column(nullable = true)
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAuthDetail user;

    public EmailConfirmationToken() {}

    public EmailConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime confirmedAt, UserAuthDetail user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public UserAuthDetail getUser() {
        return user;
    }

    public void setUser(UserAuthDetail user) {
        this.user = user;
    }
}
