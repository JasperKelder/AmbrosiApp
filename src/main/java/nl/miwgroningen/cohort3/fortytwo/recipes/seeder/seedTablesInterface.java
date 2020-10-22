package nl.miwgroningen.cohort3.fortytwo.recipes.seeder;

import java.io.IOException;

public interface seedTablesInterface {

    void seedCategory();

    void seedCuisine();

    void seedUser();

    void seedRole();

    void seedCookbook();

    void seedMeasuringUnit();

    void seedIngredient();

    void seedRecipeIngredient();

    void seedPreperationStep();

    void seedRecipe() throws IOException;
}
