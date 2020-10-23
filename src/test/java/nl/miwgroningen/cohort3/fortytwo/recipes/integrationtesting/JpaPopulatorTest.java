package nl.miwgroningen.cohort3.fortytwo.recipes.integrationtesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cuisine;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CuisineRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.seeder.JpaPopulator;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class JpaPopulatorTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Mock
    JpaPopulator jpaPopulator;

    @Test
    @DisplayName("Should get amount of >0 seeds entered in the category seeder")
    public void ShouldGetRightAmountOfCategoryDataFromDatabase() {

        jpaPopulator.seedCategory();

        List<Category> allCategories = categoryRepository.findAll();
        Assert.assertTrue("There is no data stored", allCategories.size() > 0);
    }

    @Test
    @DisplayName("Should get amount of >0 seeds entered in the cuisine seeder")
    public void ShouldGetRightAmountOfCuisineDataFromDatabase() {

        jpaPopulator.seedCuisine();

        List<Cuisine> allCuisines = cuisineRepository.findAll();
        Assert.assertTrue("There is no data stored", allCuisines.size() > 0);
    }
}
