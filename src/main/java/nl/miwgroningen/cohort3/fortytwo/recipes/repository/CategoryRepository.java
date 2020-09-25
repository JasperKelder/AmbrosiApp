package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
