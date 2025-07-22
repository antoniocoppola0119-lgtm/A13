package testrobotchallenge.commons.models.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.user.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JwtValidationResponseDTO {
    private boolean valid;
    private Role role;
    private String error;
    private String message;

    public JwtValidationResponseDTO(boolean valid, Role role) {
        this.valid = valid;
        this.role = role;
    }

    public JwtValidationResponseDTO(boolean valid, String error, String message) {
        this.valid = valid;
        this.error = error;
        this.message = message;
    }
}
