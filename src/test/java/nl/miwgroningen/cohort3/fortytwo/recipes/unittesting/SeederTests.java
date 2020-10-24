package nl.miwgroningen.cohort3.fortytwo.recipes.unittesting;

import nl.miwgroningen.cohort3.fortytwo.recipes.seeder.JpaPopulator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class SeederTests {

    @InjectMocks
    JpaPopulator jpaPopulator;

    @Test
    @DisplayName("Checks if recipeImageToByteArray method from the JpaPopulator gives a byte []")
    public void imageFromFileToByteArray() throws IOException {

        String imageFromFilepath = "src/main/resources/static/images/demo/1- Salted-caramel-taart.jpg";

        byte[] imageTypeByteArray = jpaPopulator.imageFromFileToByteArray(imageFromFilepath);

        Assert.assertTrue("Should return a byte array but it did not", imageTypeByteArray instanceof byte[]);

    }
}
