package nl.miwgroningen.cohort3.fortytwo.recipes.integrationtesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Note: a test must be written without a type so for example:
 *
 * public class and public methods will not be interpreted by Travis
 * Write all classes without a type like below!
 *
 * **/

@SpringBootTest
class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Save a category and check if the saved category is the same as stored")
    @Transactional
    void saveAndGetCategory(){
        Category newCategory = new Category("English");
        categoryRepository.save(newCategory);

        Category categoryFound = categoryRepository.getOne(newCategory.getCategoryId());

        assertEquals(newCategory, categoryFound);

    }

}
