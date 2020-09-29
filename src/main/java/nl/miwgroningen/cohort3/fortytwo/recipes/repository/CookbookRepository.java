package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

}
