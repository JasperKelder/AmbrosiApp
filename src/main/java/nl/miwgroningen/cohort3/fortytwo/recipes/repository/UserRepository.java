package nl.miwgroningen.cohort3.fortytwo.recipes.repository;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET first_name = ?, last_name = ? WHERE user_id = ?", nativeQuery = true)
    void updateName(String firstName, String lastName, Integer userId);
}


