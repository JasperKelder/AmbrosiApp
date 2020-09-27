package nl.miwgroningen.cohort3.fortytwo.recipes;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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
    @Transactional
    void saveAndGetCategory(){
        Category newCategory = new Category("English");
        categoryRepository.save(newCategory);

        Category categoryFound = categoryRepository.getOne(newCategory.getCategoryId());

        Assert.assertEquals(newCategory, categoryFound);

    }

}
