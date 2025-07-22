package testrobotchallenge.commons.models.dto.score.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EvosuiteScoreDTO {
    private CoverageDTO lineCoverageDTO;
    private CoverageDTO branchCoverageDTO;
    private CoverageDTO cBranchCoverageDTO;
    private CoverageDTO methodCoverageDTO;
    private CoverageDTO exceptionCoverageDTO;
    private CoverageDTO outputCoverageDTO;
    private CoverageDTO weakMutationCoverageDTO;
    private CoverageDTO methodNoExceptionCoverageDTO;
}
