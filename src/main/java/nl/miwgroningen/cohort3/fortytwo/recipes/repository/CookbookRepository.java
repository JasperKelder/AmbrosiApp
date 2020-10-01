package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import com.sun.jdi.Value;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM recipes.cookbook WHERE user_id = ?", nativeQuery = true)
    List<Cookbook> getCookbookByUserId(Integer userId);


}
