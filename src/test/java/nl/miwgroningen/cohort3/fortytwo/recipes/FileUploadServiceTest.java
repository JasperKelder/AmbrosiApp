package nl.miwgroningen.cohort3.fortytwo.recipes;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class FileUploadServiceTest extends RecipesApplicationTests {

    @Test
    public void mustBeFalse() {
        // Given
        Assert.assertEquals("Yes", "False");
    }
}