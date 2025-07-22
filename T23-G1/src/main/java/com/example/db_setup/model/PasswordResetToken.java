package com.example.db_setup.model;

import lombok.*;
import testrobotchallenge.commons.models.user.Role;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "password_reset_tokens", schema = "studentsrepo")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue
    private long id;
    private String token;
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;
    private Instant expiryDate;
    private boolean revoked;

    public void setPlayer(Player player) {
        this.player = player;
        this.admin = null;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        this.player = null;
    }
}
