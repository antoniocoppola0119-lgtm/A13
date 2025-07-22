package testrobotchallenge.commons.models.score;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class JacocoScore {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "covered", column = @Column(name = "jacoco_line_covered")),
            @AttributeOverride(name = "missed", column = @Column(name = "jacoco_line_missed"))
    })
    private Coverage lineCoverage;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "covered", column = @Column(name = "jacoco_branch_covered")),
            @AttributeOverride(name = "missed", column = @Column(name = "jacoco_branch_missed"))
    })
    private Coverage branchCoverage;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "covered", column = @Column(name = "jacoco_instruction_covered")),
            @AttributeOverride(name = "missed", column = @Column(name = "jacoco_instruction_missed"))
    })
    private Coverage instructionCoverage;
}