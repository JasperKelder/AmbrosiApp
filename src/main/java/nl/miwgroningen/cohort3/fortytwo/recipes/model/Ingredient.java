package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(mappedBy="ingredients")
    private List<Recipe> recipes;

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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
