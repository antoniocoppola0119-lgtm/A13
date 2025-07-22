package com.example.db_setup.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtValidationResult {
    private boolean valid;
    private String error;
    private String message;

    public JwtValidationResult(boolean valid, String error, String message) {
        this.valid = valid;
        this.error = error;
        this.message = message;
    }

}
