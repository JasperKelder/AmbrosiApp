package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Ingredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByIngredientName(String ingredientName);

    @Transactional
    @Query(value = "SELECT DISTINCT ingredient_name FROM recipes.ingredient;", nativeQuery = true)
    List<String> getDistinctIngredientNames();
}

