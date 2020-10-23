package nl.miwgroningen.cohort3.fortytwo.recipes.integrationtesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.seeder.JpaPopulator;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class JpaPopulatorTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    MeasuringUnitRepository measuringUnitRepository;

    @Autowired
    PreparationStepRepository preparationStepRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Mock
    JpaPopulator jpaPopulator;

    @Test
    @DisplayName("Should get amount of >0 seeds entered in the category seeder")
    public void ShouldGetAmountHigherThanZeroOfAllDataFromDatabase() {

        jpaPopulator.seedCategory();

        List<Category> allCategories = categoryRepository.findAll();
        List<Cuisine> allCuisines = cuisineRepository.findAll();
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        List<MeasuringUnit> allMeasuringUnits = measuringUnitRepository.findAll();
        List<PreparationStep> allPreparationSteps = preparationStepRepository.findAll();
        List<RecipeIngredient> allRecipeIngredients = recipeIngredientRepository.findAll();
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<Role> allRoles = roleRepository.findAll();
        List<User> allUsers = userRepository.findAll();

        Assert.assertTrue("There is no data stored", allCategories.size() > 0);
        Assert.assertTrue("There is no data stored", allCuisines.size() > 0);
        Assert.assertTrue("There is no data stored", allIngredients.size() > 0);
        Assert.assertTrue("There is no data stored", allMeasuringUnits.size() > 0);
        Assert.assertTrue("There is no data stored", allPreparationSteps.size() > 0);
        Assert.assertTrue("There is no data stored", allRecipeIngredients.size() > 0);
        Assert.assertTrue("There is no data stored", allRecipes.size() > 0);
        Assert.assertTrue("There is no data stored", allRoles.size() > 0);
        Assert.assertTrue("There is no data stored", allUsers.size() > 0);


    }

}
