package testrobotchallenge.commons.mappers;

import testrobotchallenge.commons.models.dto.score.basic.CoverageDTO;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;
import testrobotchallenge.commons.models.score.Coverage;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

public class ScoreDTOMapper {
    public static EvosuiteScoreDTO toEvosuiteScoreDTO(EvosuiteScore score) {
        if (score == null)
            return null;

        EvosuiteScoreDTO dto = new EvosuiteScoreDTO();
        dto.setLineCoverageDTO(toCoverageDTO(score.getLineCoverage()));
        dto.setBranchCoverageDTO(toCoverageDTO(score.getBranchCoverage()));
        dto.setExceptionCoverageDTO(toCoverageDTO(score.getExceptionCoverage()));
        dto.setWeakMutationCoverageDTO(toCoverageDTO(score.getWeakMutationCoverage()));
        dto.setOutputCoverageDTO(toCoverageDTO(score.getOutputCoverage()));
        dto.setMethodCoverageDTO(toCoverageDTO(score.getMethodCoverage()));
        dto.setMethodNoExceptionCoverageDTO(toCoverageDTO(score.getMethodNoExceptionCoverage()));
        dto.setCBranchCoverageDTO(toCoverageDTO(score.getCBranchCoverage()));

        return dto;
    }

    public static JacocoScoreDTO toJacocoScoreDTO(JacocoScore score) {
        if (score == null)
            return null;

        JacocoScoreDTO dto = new JacocoScoreDTO();
        dto.setLineCoverageDTO(toCoverageDTO(score.getLineCoverage()));
        dto.setBranchCoverageDTO(toCoverageDTO(score.getBranchCoverage()));
        dto.setInstructionCoverageDTO(toCoverageDTO(score.getInstructionCoverage()));

        return dto;
    }

    private static CoverageDTO toCoverageDTO(Coverage coverage) {
        if (coverage == null) return null;
        CoverageDTO dto = new CoverageDTO();
        dto.setCovered(coverage.getCovered());
        dto.setMissed(coverage.getMissed());
        return dto;
    }

}
