package testrobotchallenge.commons.models.score;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class Coverage {
    private int covered;
    private int missed;
}
