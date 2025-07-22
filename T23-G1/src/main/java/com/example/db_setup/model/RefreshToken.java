package com.example.db_setup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testrobotchallenge.commons.models.user.Role;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "refresh_tokens", schema = "studentsrepo")
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;
    private Instant expiryDate;
    private boolean revoked = false;

    public void setPlayer(Player player) {
        this.player = player;
        this.admin = null;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        this.player = null;
    }
}

