package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecipeIngredientsKey implements Serializable {
    @Column(name = "recipe_id")
    public Integer recipeId;

    @Column(name = "ingredient_id")
    public Integer ingredientId;

    public RecipeIngredientsKey() {
    }

    public int hashCode() {
        return (int)this.recipeId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RecipeIngredientsKey)) {
            return false;
        }
        RecipeIngredientsKey recipeIngredientsKey = (RecipeIngredientsKey) obj;
        return recipeIngredientsKey.recipeId.equals(this.recipeId)
                && recipeIngredientsKey.ingredientId.equals(this.ingredientId);
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }
}
