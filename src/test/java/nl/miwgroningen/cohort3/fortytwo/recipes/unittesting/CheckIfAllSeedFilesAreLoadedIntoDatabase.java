package nl.miwgroningen.cohort3.fortytwo.recipes.unittesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.miwgroningen.cohort3.fortytwo.recipes.config.JpaPopulator;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CheckIfAllSeedFilesAreLoadedIntoDatabase {

    @Autowired
    CategoryRepository categoryRepository;

    @Mock
    JpaPopulator jpaPopulator;

    @Test
    @DisplayName("Should get amount of seeds entered in the category JSON file")
    public void ShouldGetRightAmountOfCategoryDataFromDatabase() throws JsonProcessingException {

        jpaPopulator.getRespositoryPopulator();

        List<Category> allCategories = categoryRepository.findAll();
        Assert.assertEquals("There are more data objects than expected", 2, allCategories.size());

    }
}
