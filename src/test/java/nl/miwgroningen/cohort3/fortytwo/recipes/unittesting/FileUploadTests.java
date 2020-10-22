package nl.miwgroningen.cohort3.fortytwo.recipes.unittesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class FileUploadTests {

    @InjectMocks
    FileUploadService fileUploadService = new FileUploadService();

    @InjectMocks
    Recipe recipe = new Recipe();

    @InjectMocks
    Category category = new Category();

    @Test
    @DisplayName("Checks if the output of convertImageToBase64 method is a string")
    public void convertImageToBase64ShouldReturnString() {
        category.setCategoryName("Asian");
        category.setCategoryId(1);
        recipe.setImage(null);
        recipe.setCategoryName(category);

        String imageShouldBeTypeString = fileUploadService.convertToBase64(recipe);

        Assert.assertTrue(imageShouldBeTypeString instanceof String);

    }

}