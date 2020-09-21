package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.junit.Assert;
import org.junit.Test;

public class FileUploadServiceTest {

    @Test
    public void convertToBase64WithNullImageTest() {
        // Given
        FileUploadService fileUploadServiceTest = new FileUploadService();
        Recipe recipe = new Recipe(null);
        Assert.assertTrue(fileUploadServiceTest.convertToBase64(recipe) instanceof String);
    }

}