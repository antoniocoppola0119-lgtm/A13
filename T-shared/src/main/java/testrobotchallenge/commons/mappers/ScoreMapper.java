package testrobotchallenge.commons.mappers;

import testrobotchallenge.commons.models.dto.score.basic.CoverageDTO;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;
import testrobotchallenge.commons.models.score.Coverage;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

public class ScoreMapper {

    public static EvosuiteScore toEvosuiteScore(EvosuiteScoreDTO dto) {
        if (dto == null)
            return null;

        EvosuiteScore evosuite = new EvosuiteScore();
        evosuite.setLineCoverage(toCoverage(dto.getLineCoverageDTO()));
        evosuite.setBranchCoverage(toCoverage(dto.getBranchCoverageDTO()));
        evosuite.setExceptionCoverage(toCoverage(dto.getExceptionCoverageDTO()));
        evosuite.setWeakMutationCoverage(toCoverage(dto.getWeakMutationCoverageDTO()));
        evosuite.setOutputCoverage(toCoverage(dto.getOutputCoverageDTO()));
        evosuite.setMethodCoverage(toCoverage(dto.getMethodCoverageDTO()));
        evosuite.setMethodNoExceptionCoverage(toCoverage(dto.getMethodNoExceptionCoverageDTO()));
        evosuite.setCBranchCoverage(toCoverage(dto.getCBranchCoverageDTO()));

        return evosuite;
    }

    public static JacocoScore toJacocoScore(JacocoScoreDTO dto) {
        if (dto == null)
            return null;

        JacocoScore jacoco = new JacocoScore();
        jacoco.setLineCoverage(toCoverage(dto.getLineCoverageDTO()));
        jacoco.setBranchCoverage(toCoverage(dto.getBranchCoverageDTO()));
        jacoco.setInstructionCoverage(toCoverage(dto.getInstructionCoverageDTO()));
        
        return jacoco;
    }

    private static Coverage toCoverage(CoverageDTO dto) {
        if (dto == null) return null;
        Coverage coverage = new Coverage();
        coverage.setCovered(dto.getCovered());
        coverage.setMissed(dto.getMissed());
        return coverage;
    }
}
