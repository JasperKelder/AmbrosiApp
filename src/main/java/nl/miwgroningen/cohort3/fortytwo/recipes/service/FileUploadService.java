package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * For the sake of overview, we have made this service which contains:
 *  error handling for uploading a file
 *  Converter to convert byte[] images from the Mysql database to Base64 so the html can read them.
 * **/

public class FileUploadService {

    public String convertToBase64(Recipe recipe) {
        String imageInBase64 = "";
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
        return imageInBase64;
    }
}
