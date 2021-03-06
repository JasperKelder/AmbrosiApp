package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
public class RecipeIngredient {

    @EmbeddedId
    RecipeIngredientsKey recipeIngredientsKey = new RecipeIngredientsKey();

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Expose
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Expose
    private Integer quantity;

    // constructors:
    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, Integer quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Integer quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    // getters and setters:
    public RecipeIngredientsKey getRecipeIngredientsKey() {
        return recipeIngredientsKey;
    }
    public void setRecipeIngredientsKey(RecipeIngredientsKey recipeIngredientsKey) {
        this.recipeIngredientsKey = recipeIngredientsKey;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public Ingredient getIngredient() {
        return ingredient;
    }
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
