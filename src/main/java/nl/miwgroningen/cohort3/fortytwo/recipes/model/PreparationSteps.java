package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PreparationSteps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preparationStepId;

    @Column(columnDefinition = "TEXT")
    private String preparationStep;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_preparationSteps",
            joinColumns = @JoinColumn(
                    name = "preparationStep_Id", referencedColumnName = "preparationStepId"),
            inverseJoinColumns = @JoinColumn(
                    name = "recipe_Id" , referencedColumnName = "recipeId" ))
    private List<Recipe> recipes;

    // Constructor
    public PreparationSteps() { }
    public PreparationSteps(String preparationStep) { this.preparationStep = preparationStep; }

    //Gettters and setters
    public Integer getPreparationStepId() { return preparationStepId; }
    public void setPreparationStepId(Integer preparationStepId) { this.preparationStepId = preparationStepId; }
    public String getPreparationStep() { return preparationStep; }
    public void setPreparationStep(String preparationStep) { this.preparationStep = preparationStep; }
    public List<Recipe> getRecipes() { return recipes; }
    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }
}

