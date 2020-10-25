package nl.miwgroningen.cohort3.fortytwo.recipes.unittesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Role;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    Role role = new Role();

    @InjectMocks
    User user = new User();

    @Test
    @DisplayName("Checks if a user has an admin role (true)")
    public void hasAdminRole_userAdmin_True() {
        // arrange
        role.setName("ROLE_ADMIN");
        List<Role> roles = Arrays.asList(role);
        user.setRoles(roles);

        // act
        boolean hasAdminRole = user.hasAdminRole();

        // assert
        Assert.assertTrue(hasAdminRole);
    }

    @Test
    @DisplayName("Checks if a user has an admin role (false)")
    public void hasAdminRole_userAdmin_False() {
        // arrange
        role.setName("ROLE_USER");
        List<Role> roles = Arrays.asList(role);
        user.setRoles(roles);

        // act
        boolean hasAdminRole = user.hasAdminRole();

        // assert
        Assert.assertFalse(hasAdminRole);
    }
}