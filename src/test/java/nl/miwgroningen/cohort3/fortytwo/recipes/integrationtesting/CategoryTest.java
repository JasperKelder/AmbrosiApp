package nl.miwgroningen.cohort3.fortytwo.recipes.integrationtesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@SpringBootTest(properties="spring.main.lazy-initialization=true")
class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Mock
    Category newCategory;

    @Test
    @DisplayName("Save a category and check if the saved category is the same as stored")
    @Transactional
    void saveAndGetCategory(){
        newCategory = new Category("English");
        categoryRepository.save(newCategory);

        Category categoryFound = categoryRepository.getOne(newCategory.getCategoryId());

        assertEquals(newCategory, categoryFound);

    }

}
