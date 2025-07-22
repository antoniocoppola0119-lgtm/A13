package testrobotchallenge.commons.models.dto.score.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JacocoScoreDTO {
    private CoverageDTO lineCoverageDTO;
    private CoverageDTO branchCoverageDTO;
    private CoverageDTO instructionCoverageDTO;
}
