package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Ingredient implements Comparable<Ingredient> {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Expose
    @Column(unique = true)
    private String ingredientName;

    @Expose(serialize = false)
    @OneToMany(mappedBy="ingredient")
    private Set<RecipeIngredient> recipeIngredients;

    @Expose
    private String measuring_unit;

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

    public String getMeasuring_unit() {
        return measuring_unit;
    }

    public void setMeasuring_unit(String measuring_unit) {
        this.measuring_unit = measuring_unit;
    }
}
