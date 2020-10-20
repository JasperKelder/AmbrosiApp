package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Ingredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.PreparationSteps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface PreparationStepRepository extends JpaRepository<PreparationSteps, Integer> {

}
