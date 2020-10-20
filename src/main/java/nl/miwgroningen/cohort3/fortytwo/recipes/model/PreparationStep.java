package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PreparationStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preparationStepId;

    @Column(columnDefinition = "TEXT")
    private String preparationStep;


    // Constructor
    public PreparationStep() { }
    public PreparationStep(String preparationStep) { this.preparationStep = preparationStep; }

    //Gettters and setters
    public Integer getPreparationStepId() { return preparationStepId; }
    public void setPreparationStepId(Integer preparationStepId) { this.preparationStepId = preparationStepId; }
    public String getPreparationStep() { return preparationStep; }
    public void setPreparationStep(String preparationStep) { this.preparationStep = preparationStep; }
}

