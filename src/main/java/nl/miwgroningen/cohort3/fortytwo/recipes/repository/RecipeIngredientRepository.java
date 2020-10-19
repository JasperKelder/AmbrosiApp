package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.RecipeIngredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.RecipeIngredientsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientsKey> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `recipe_ingredient` WHERE recipe_id = ?", nativeQuery = true)
    void deleteRecipeIngredientsByRecipeId(Integer recipeId);
}

