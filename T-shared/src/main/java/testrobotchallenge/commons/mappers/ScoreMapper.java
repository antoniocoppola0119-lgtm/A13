package testrobotchallenge.commons.mappers;

import testrobotchallenge.commons.models.dto.score.basic.CoverageDTO;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;
import testrobotchallenge.commons.models.score.Coverage;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

/**
 * Classe di mapping usata per mappare oggetti {@link EvosuiteScoreDTO}, {@link EvosuiteScoreDTO} e {@link CoverageDTO}
 * nelle corrispondenti classi interne del dominio.
 */
public class ScoreMapper {

    private ScoreMapper() {
        throw new IllegalStateException("Classe di mapping usata per mappare oggetti del dominio nei rispettivi DTO");
    }

    /**
     * Converte un {@link EvosuiteScoreDTO} in un oggetto interno {@link EvosuiteScore}.
     *
     * @param dto   il DTO da convertire
     * @return un oggetto {@link EvosuiteScore} popolato oppure {@code null} se l'input è null
     */
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

    /**
     * Converte un {@link JacocoScoreDTO} in un oggetto interno {@link JacocoScore}.
     *
     * @param dto   il DTO da convertire
     * @return un oggetto {@link JacocoScore} popolato oppure {@code null} se l'input è null
     */
    public static JacocoScore toJacocoScore(JacocoScoreDTO dto) {
        if (dto == null)
            return null;

        JacocoScore jacoco = new JacocoScore();
        jacoco.setLineCoverage(toCoverage(dto.getLineCoverageDTO()));
        jacoco.setBranchCoverage(toCoverage(dto.getBranchCoverageDTO()));
        jacoco.setInstructionCoverage(toCoverage(dto.getInstructionCoverageDTO()));

        return jacoco;
    }

    /**
     * Converte un {@link CoverageDTO} in un oggetto interno {@link Coverage}.
     *
     * @param dto   il DTO da convertire
     * @return un oggetto {@link Coverage} popolato oppure {@code null} se l'input è null
     */
    private static Coverage toCoverage(CoverageDTO dto) {
        if (dto == null) return null;
        Coverage coverage = new Coverage();
        coverage.setCovered(dto.getCovered());
        coverage.setMissed(dto.getMissed());
        return coverage;
    }
}
