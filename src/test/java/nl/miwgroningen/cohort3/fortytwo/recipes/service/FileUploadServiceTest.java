package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileUploadServiceTest {

    @Test
    public void convertToBase64WithNullImageTest() {
        // Given
        Assert.assertEquals("Yes", "False");
    }
}