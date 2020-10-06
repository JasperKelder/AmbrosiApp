package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM recipe WHERE LOWER(recipe_title) LIKE LOWER(CONCAT('%',:term,'%'));", nativeQuery = true)
    List<Recipe> getSuggestions(@Param("term") String term);

    @Transactional
    @Query(value = "SELECT r.recipe_id, r.cooktime, r.image, r.preperation_time, r.recipe_preperation, " +
            "r.recipe_title, r.servings, r.category_id, r.cuisine_id, r.user_id " +
            "FROM recipe AS r JOIN recipe_ingredients AS ri ON ri.recipe_id = r.recipe_id \n" +
            "JOIN ingredient AS i ON ri.ingredient_id = i.ingredient_id " +
            "WHERE LOWER(i.ingredient_name) LIKE LOWER(CONCAT('%',:term,'%'));", nativeQuery = true)
    List<Recipe> getSuggestionsByIngredient(@Param("term") String term);

    @Transactional
    @Query(value = "SELECT * FROM recipes.recipe WHERE category_id = ?;", nativeQuery = true)
    List<Recipe> categoryFilter(@Param("categoryId") int categoryId);
}


