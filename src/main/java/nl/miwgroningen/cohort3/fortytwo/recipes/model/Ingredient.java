package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ingredient implements Comparable<Ingredient> {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Expose
    @Column
    private String ingredientName;

    @OneToMany(mappedBy="ingredient")
    private Set<RecipeIngredient> recipeIngredients;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measuringUnitId", referencedColumnName = "measuringUnitId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MeasuringUnit measuringUnit;

    @Column
    boolean validated;

    @Override
    public String toString() {
        return ingredientName;
    }

    @Override
    public int compareTo(Ingredient ingredient) {
        return ingredientName.compareTo(ingredient.getIngredientName());
    }

    public Ingredient() {
    }

    public Ingredient(String ingredientName, MeasuringUnit measuringUnit, boolean validated) {
        this.ingredientName = ingredientName;
        this.measuringUnit = measuringUnit;
        this.validated = validated;
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    // Getters and setters:
    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public MeasuringUnit getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(MeasuringUnit measuring_unit) {
        this.measuringUnit = measuring_unit;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
