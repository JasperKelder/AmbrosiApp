package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

public class FileUploadServiceTest {

    @Test
    public void convertToBase64() {
        // Given
        Recipe recipe = new Recipe(null);
        String imageInBase64 = "";

        // When
        try {
            // Check if there is an image uploaded, if there is not set the recipe.image to the default image
            if (recipe.getImage() == null){
                File image = new File("src/main/resources/static/images/food.jpg");
                FileInputStream imageInFile = new FileInputStream(image);
                byte[] imageInBytes = imageInFile.readAllBytes();
                imageInBase64 += Base64.getEncoder().encodeToString(imageInBytes);
            }
            // if the user uploaded an image then convert it to Base64
            else {
                imageInBase64 += Base64.getEncoder().encodeToString(recipe.getImage());
            }
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        Assert.assertTrue(imageInBase64 instanceof String);
    }

}