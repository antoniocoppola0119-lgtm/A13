package testrobotchallenge.commons.models.dto.score;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;

@Getter
@Setter
@NoArgsConstructor
public class EvosuiteCoverageDTO {
    private EvosuiteScoreDTO evosuiteScoreDTO;
    private String resultFileContent;
}
