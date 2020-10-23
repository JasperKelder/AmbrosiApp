package nl.miwgroningen.cohort3.fortytwo.recipes.seeder;

import java.io.IOException;

public interface seedTablesInterface {

    void seedCategory();

    void seedCuisine();

    void seedUser();

    void seedRecipeAndCookbookAndPreparationSteps() throws IOException;

    void seedMeasuringUnit();

    void seedIngredient();

    void seedRecipeIngredient();

    void seedCookbook();


}
