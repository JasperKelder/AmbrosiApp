package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.RecipeIngredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.RecipeIngredientsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientsKey> {
}
