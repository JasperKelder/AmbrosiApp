package nl.miwgroningen.cohort3.fortytwo.recipes.seeder;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.UserRegistrationDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
* This class will take JSON objects and convert it to datasources which can be interpreted by the SQL database.
* */
@Component
public class JpaPopulator implements CommandLineRunner, seedTablesInterface {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    MeasuringUnitRepository measuringUnitRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    PreparationStepRepository preparationStepRepository;

    @Override
    public void run(String... args) throws Exception {
        seedCategory();
        seedCuisine();
        seedUser();
        seedRecipeAndCookbookAndPreparationSteps();
        seedMeasuringUnit();
        seedIngredient();
        seedRecipeIngredient();
    }

    public void seedCategory() {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category("Breakfast");
            Category category2 = new Category("Lunch");
            Category category3 = new Category("Dinner");
            Category category4 = new Category("Snacks");
            Category category5 = new Category("Cheating");

            categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5));
        }
    }

    @Override
    public void seedCuisine() {
        if (cuisineRepository.count() == 0) {
            Cuisine cuisine1 = new Cuisine("Italien");
            Cuisine cuisine2 = new Cuisine("Asian");
            Cuisine cuisine3 = new Cuisine("Dutch");
            Cuisine cuisine4 = new Cuisine("Oriental");
            Cuisine cuisine5 = new Cuisine("Spanisch");
            Cuisine cuisine6 = new Cuisine("Greek");

            cuisineRepository.saveAll(Arrays.asList(cuisine1, cuisine2, cuisine3, cuisine4, cuisine5, cuisine6));
        }
    }

    @Override
    public void seedUser() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            //Normally only admin user is added with seeder, for demo purpose also added 4 team members as users
            User userAdmin = new User("Admin", "Admin", "admin@admin.nl", "password");
            User user1 = new User("Reinout", "Smit", "reinout@smit.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user2 = new User("Jasper", "Kelder", "jasper@kelder.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user3 = new User("Jasmijn", "van der Veen", "jasmijn@vanderveen.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user4 = new User("Nathalie", "Antoine", "nathalie@antoine.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            userAdmin.setRoles(Arrays.asList(roleAdmin));
            user1.setRoles(Arrays.asList(roleUser));
            user2.setRoles(Arrays.asList(roleUser));
            user3.setRoles(Arrays.asList(roleUser));
            user4.setRoles(Arrays.asList(roleUser));

            userRepository.saveAll(Arrays.asList(userAdmin, user1, user2, user3, user4));
        }
    }

    @Override
    public void seedRecipeAndCookbookAndPreparationSteps() throws IOException {

        // First declare the images you want to use in the recipes
        byte[] caramelpieImage = imageFromFileToByteArray("src/main/resources/static/images/demo/1- Salted-caramel-taart.jpg");

        // Add the preperationSteps per recipe and save them to a list
        PreparationStep preparationStep1 = new PreparationStep("Grind the cookies in the food processor. Meanwhile, melt 100 grams of butter and stir into the cookie crumbs.");
        PreparationStep preparationStep2 = new PreparationStep("Line a tin (we use a 24cm tin) with parchment paper, grease the sides of the tin with some butter and then divide the biscuit mixture over the bottom. Make sure you also make the border about 2 inches high. Press down well with a spoon and put in it in the fridge.");
        PreparationStep preparationStep3 = new PreparationStep("Now melt the rest of the butter in a pot. Then add the condensed milk and caster sugar and keep stirring. It takes about 10 minutes for the caramel to have the right consistency.");
        PreparationStep preparationStep4 = new PreparationStep("After 5 minutes, add 2 teaspoons of sea salt and keep stirring.");
        PreparationStep preparationStep5 = new PreparationStep("Remove the tin from the refrigerator and divide the caramel over it. Put the tin back in the refrigerator and let it cool for an hour.");
        PreparationStep preparationStep6 = new PreparationStep("Meanwhile, melt the chocolate together with 100 milliliters of milk au-bain marie (in a bowl over a pan of boiling water). Pour the chocolate over the caramel layer once it has cooled down properly. Put the cake in the fridge for another 30 minutes, so that the chocolate hardens.");
        PreparationStep preparationStep7 = new PreparationStep("Melt 100 grams of fudge with the rest of the milk in a pot until caramel. In the meantime, cut the rest of the fudge into small cubes. Finish the cake with the fudge cubes, 2 teaspoons of sea salt and a drizzle of the caramel on top!");

        ArrayList<PreparationStep> preparationStepsRecipe1 = new ArrayList<>(Arrays.asList(preparationStep1, preparationStep2, preparationStep3, preparationStep4, preparationStep5, preparationStep6, preparationStep7));

        // Initialize the recipes
        Recipe recipe1 = new Recipe(
                    "Salted caramel pie",
                    30,
                    10,
                    60,
                    cuisineRepository.getOne(3),
                    categoryRepository.getOne(5),
                    userRepository.getOne(4),
                caramelpieImage
            );

        Recipe recipe2 = new Recipe(
                "Salted caramel pie",
                30,
                10,
                60,
                cuisineRepository.getOne(3),
                categoryRepository.getOne(5),
                userRepository.getOne(3),
                caramelpieImage
        );

        // Add the preperationSteps to the recipe and add them to the cookbook of choice
        recipe1.setPreparationStepList(preparationStepsRecipe1);
//        recipe2.setPreparationStepList(preparationStepsRecipe2);
        ArrayList<Recipe> recipesInCookbook1 = new ArrayList<>(Arrays.asList(recipe1));

        ArrayList<Recipe> recipesInCookbook2 = new ArrayList<>(Arrays.asList(recipe2));

        if (preparationStepRepository.count() == 0 && cookbookRepository.count() == 0 &&
                recipeRepository.count() == 0) {
            Cookbook cookbook1 = new Cookbook(false, "Jasmijn her favorite cookbook", userRepository.getOne(4));
            Cookbook cookbook2 = new Cookbook(false, "Reinout his favorite cookbook", userRepository.getOne(2));

            cookbook1.setRecipes(recipesInCookbook1);
            cookbook2.setRecipes(recipesInCookbook2);
            cookbookRepository.saveAll(Arrays.asList(cookbook1, cookbook2));
        }
    }

    @Override
    public void seedMeasuringUnit() {
        if (measuringUnitRepository.count() == 0) {
            MeasuringUnit measuringUnit1 = new MeasuringUnit("", "");
            MeasuringUnit measuringUnit2 = new MeasuringUnit("gram", "gr");
            MeasuringUnit measuringUnit3 = new MeasuringUnit("milliliter", "ml");
            MeasuringUnit measuringUnit4 = new MeasuringUnit("pieces", "pcs");
            MeasuringUnit measuringUnit5 = new MeasuringUnit("liter", "l");
            MeasuringUnit measuringUnit6 = new MeasuringUnit("teaspoon", "tsp");
            MeasuringUnit measuringUnit7 = new MeasuringUnit("tablespoon", "tbsp");

            ArrayList<MeasuringUnit> measuringUnits = new ArrayList<>(Arrays.asList(measuringUnit1, measuringUnit2,
                    measuringUnit3, measuringUnit4, measuringUnit5, measuringUnit6, measuringUnit7));

            measuringUnitRepository.saveAll(measuringUnits);
        }
    }

    @Override
    public void seedIngredient() {
        if (ingredientRepository.count() == 0) {
            Ingredient condensedMilk = new Ingredient("condensed milk",measuringUnitRepository.getOne(2), true);
            Ingredient casterSugar = new Ingredient("caster sugar",measuringUnitRepository.getOne(2), true);
            Ingredient milkChocolate = new Ingredient("milk chocolate",measuringUnitRepository.getOne(2), true);
            Ingredient butterCookies = new Ingredient("butter cookies",measuringUnitRepository.getOne(2), true);
            Ingredient unsaltedButter = new Ingredient("unsalted butter",measuringUnitRepository.getOne(2), true);
            Ingredient milk = new Ingredient("milk",measuringUnitRepository.getOne(3), true);
            Ingredient seaSalt = new Ingredient("sea salt",measuringUnitRepository.getOne(6), true);
            Ingredient fudge = new Ingredient("fudge",measuringUnitRepository.getOne(2), true);

            ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(condensedMilk, casterSugar, milkChocolate, butterCookies, unsaltedButter, milk, seaSalt, fudge));
            ingredientRepository.saveAll(ingredients);
        }
    }

    @Override
    public void seedRecipeIngredient() {
        if (recipeIngredientRepository.count() == 0) {
            RecipeIngredient recipeIngredient1 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(1), 400);
            RecipeIngredient recipeIngredient2 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(2), 75);
            RecipeIngredient recipeIngredient3 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(3), 250);
            RecipeIngredient recipeIngredient4 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(4), 350);
            RecipeIngredient recipeIngredient5 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(5), 175);
            RecipeIngredient recipeIngredient6 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(6), 100);
            RecipeIngredient recipeIngredient7 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(7), 4);
            RecipeIngredient recipeIngredient8 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(8), 130);

            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>(Arrays.asList(recipeIngredient1, recipeIngredient2, recipeIngredient3, recipeIngredient4, recipeIngredient5, recipeIngredient6, recipeIngredient7, recipeIngredient8));
            recipeIngredientRepository.saveAll(recipeIngredients);
        }
    }

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }
}