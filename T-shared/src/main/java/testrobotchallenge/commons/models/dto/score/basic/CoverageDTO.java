package testrobotchallenge.commons.models.dto.score.basic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoverageDTO {
    private int covered;
    private int missed;
}
