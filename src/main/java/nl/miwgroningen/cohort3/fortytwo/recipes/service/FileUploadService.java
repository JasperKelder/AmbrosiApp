package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen

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
                File image;
                // Set a default image per category
                // 1. Breakfast 2.Lunch 3.Dinner 4. Snacks 5. Cheating
                switch(recipe.getCategoryName().getCategoryId()) {
                    case 1:
                        image = new File("src/main/resources/static/images/default-category/breakfast-category.jpg");
                        break;
                    case 2:
                        image = new File("src/main/resources/static/images/default-category/lunch-category.jpg");
                        break;
                    case 3:
                        image = new File("src/main/resources/static/images/default-category/Dinner-category.jpg");
                        break;
                    case 4:
                        image = new File("src/main/resources/static/images/default-category/snack-category.jpg");
                        break;
                    case 5:
                        image = new File("src/main/resources/static/images/default-category/cheating-category.jpg");
                        break;
                    default:
                        image = new File("src/main/resources/static/images/default-category/breakfast-category.jpg");
                }
                // Set the default image file to type FileInputStream
                FileInputStream imageInFile = new FileInputStream(image);

                // Get the bytes from the file so we can convert it to Base64 String so that html can read the image
                byte[] imageInBytes = imageInFile.readAllBytes();
                imageInBase64 += Base64.getEncoder().encodeToString(imageInBytes);
            }
            // else if the user uploaded an image then convert it to Base64
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
