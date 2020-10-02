package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Transactional
    //@Modifying
    @Query(value = "SELECT * FROM recipe WHERE LOWER(recipe_title) LIKE LOWER(CONCAT('%',:term,'%'));", nativeQuery = true)
    List<Recipe> getSuggestions(@Param("term") String term);
}


