package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;

import java.io.*;
import java.util.Arrays;
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
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                        break;
                    case 2:
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                        break;
                    case 3:
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                        break;
                    case 4:
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                        break;
                    case 5:
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                        break;
                    default:
                        image = new File("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
                }
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
