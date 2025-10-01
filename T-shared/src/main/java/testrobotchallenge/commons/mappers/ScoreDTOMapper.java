package testrobotchallenge.commons.mappers;

import testrobotchallenge.commons.models.dto.score.basic.CoverageDTO;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;
import testrobotchallenge.commons.models.score.Coverage;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

/**
 * Classe di mapping usata per mappare oggetti {@link EvosuiteScore}, {@link JacocoScore} e/o {@link Coverage} nei
 * corrispettivi DTO.
 */
public class ScoreDTOMapper {

    private ScoreDTOMapper() {
        throw new IllegalStateException("Classe di mapping usata per mappare oggetti del dominio nei rispettivi DTO");
    }

    /**
     * Converte un oggetto interno {@link EvosuiteScore} in un {@link EvosuiteScoreDTO}.
     *
     * @param score     l'oggetto interno EvosuiteScore da convertire
     * @return un {@link EvosuiteScoreDTO} popolato oppure {@code null} se l'input è null
     */
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

    /**
     * Converte un oggetto interno {@link JacocoScore} in un {@link JacocoScoreDTO}.
     *
     * @param score     l'oggetto interno JacocoScore da convertire
     * @return un {@link JacocoScoreDTO} popolato oppure {@code null} se l'input è null
     */
    public static JacocoScoreDTO toJacocoScoreDTO(JacocoScore score) {
        if (score == null)
            return null;

        JacocoScoreDTO dto = new JacocoScoreDTO();
        dto.setLineCoverageDTO(toCoverageDTO(score.getLineCoverage()));
        dto.setBranchCoverageDTO(toCoverageDTO(score.getBranchCoverage()));
        dto.setInstructionCoverageDTO(toCoverageDTO(score.getInstructionCoverage()));

        return dto;
    }

    /**
     * Converte un oggetto interno {@link Coverage} in un {@link CoverageDTO}.
     *
     * @param coverage  l'oggetto interno Coverage da convertire
     * @return un {@link CoverageDTO} popolato oppure {@code null} se l'input è null
     */
    private static CoverageDTO toCoverageDTO(Coverage coverage) {
        if (coverage == null) return null;
        CoverageDTO dto = new CoverageDTO();
        dto.setCovered(coverage.getCovered());
        dto.setMissed(coverage.getMissed());
        return dto;
    }

}
