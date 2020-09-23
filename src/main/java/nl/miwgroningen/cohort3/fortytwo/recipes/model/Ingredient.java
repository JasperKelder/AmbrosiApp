package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingredient implements Comparable<Ingredient> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Column(unique = true)
    private String ingredientName;

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
