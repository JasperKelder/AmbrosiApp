package nl.miwgroningen.cohort3.fortytwo.recipes.seeder;


import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
            Cuisine cuisine1 = new Cuisine("Italian");
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
            //Normally only admin user is added with seeder, for demo purpose also added 4 team members as users, password = password
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
        byte[]caramelpieImage = imageFromFileToByteArray("src/main/resources/static/images/demo/1- Salted-caramel-taart.jpg");
        byte[]squashSaladImage = imageFromFileToByteArray("src/main/resources/static/images/demo/2- squash salad.jpg");
        byte[]chaiPuddingImage = imageFromFileToByteArray("src/main/resources/static/images/demo/3- ChiaPudding.jpg");

        // Add the preperationSteps per recipe and save them to a list
        PreparationStep preparationStep1 = new PreparationStep("Grind the cookies in the food processor. Meanwhile, melt 100 grams of butter and stir into the cookie crumbs.");
        PreparationStep preparationStep2 = new PreparationStep("Line a tin (we use a 24cm tin) with parchment paper, grease the sides of the tin with some butter and then divide the biscuit mixture over the bottom. Make sure you also make the border about 2 inches high. Press down well with a spoon and put in it in the fridge.");
        PreparationStep preparationStep3 = new PreparationStep("Now melt the rest of the butter in a pot. Then add the condensed milk and caster sugar and keep stirring. It takes about 10 minutes for the caramel to have the right consistency.");
        PreparationStep preparationStep4 = new PreparationStep("After 5 minutes, add 2 teaspoons of sea salt and keep stirring.");
        PreparationStep preparationStep5 = new PreparationStep("Remove the tin from the refrigerator and divide the caramel over it. Put the tin back in the refrigerator and let it cool for an hour.");
        PreparationStep preparationStep6 = new PreparationStep("Meanwhile, melt the chocolate together with 100 milliliters of milk au-bain marie (in a bowl over a pan of boiling water). Pour the chocolate over the caramel layer once it has cooled down properly. Put the cake in the fridge for another 30 minutes, so that the chocolate hardens.");
        PreparationStep preparationStep7 = new PreparationStep("Melt 100 grams of fudge with the rest of the milk in a pot until caramel. In the meantime, cut the rest of the fudge into small cubes. Finish the cake with the fudge cubes, 2 teaspoons of sea salt and a drizzle of the caramel on top!");
        PreparationStep preparationStep8 = new PreparationStep("Preheat the oven to 180ºC/350ºF/gas 4.");
        PreparationStep preparationStep9 = new PreparationStep("Carefully cut the squash into rough 5cm chunks (seeds and all). Then, in a roasting tray, rub all over with the harissa, 1 tablespoon of olive oil and a pinch of sea salt and black pepper.");
        PreparationStep preparationStep10 = new PreparationStep("Roast for 50 minutes, or until soft, golden and gnarly.");
        PreparationStep preparationStep11 = new PreparationStep("With a few minutes to go, place 1 tablespoon each of extra virgin olive oil and red wine vinegar, and a little salt and pepper, in a large bowl.");
        PreparationStep preparationStep12 = new PreparationStep("Halve, peel, destone, slice and toss in the avocados, then gently mix in the salad leaves.");
        PreparationStep preparationStep13 = new PreparationStep("Use forks to divide and tear the hot squash (skin, seeds and all) between your plates. Divide up the salad on top and tear over the mozzarella, then serve.");
        PreparationStep preparationStep14 = new PreparationStep("Mash the bananas using a fork until smooth (the riper the bananas are, the easier this will be).");
        PreparationStep preparationStep15 = new PreparationStep("Combine with the remaining ingredients in a mixing bowl and stir well. Place the bowl in the fridge for 15 minutes, then give the pudding a quick stir and place it back in the fridge overnight or for at least four hours.");
        PreparationStep preparationStep16 = new PreparationStep("Divide the mixture into two servings and add your topping of choice.");

        PreparationStep preparationStep17 = new PreparationStep("");
        PreparationStep preparationStep18 = new PreparationStep("");
        PreparationStep preparationStep19 = new PreparationStep("");


        ArrayList<PreparationStep> preparationStepsRecipe1 = new ArrayList<>(Arrays.asList(preparationStep1, preparationStep2, preparationStep3, preparationStep4, preparationStep5, preparationStep6, preparationStep7));
        ArrayList<PreparationStep> preparationStepsRecipe2 = new ArrayList<>(Arrays.asList(preparationStep8, preparationStep9, preparationStep10,preparationStep11, preparationStep12, preparationStep13));
        ArrayList<PreparationStep> preparationStepsRecipe3 = new ArrayList<>(Arrays.asList(preparationStep14, preparationStep15, preparationStep16));

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
                "Squash saled",
                10,
                4,
                50,
                cuisineRepository.getOne(3),
                categoryRepository.getOne(2),
                userRepository.getOne(5),
                squashSaladImage
        );

        Recipe recipe3 = new Recipe(
                "chia pudding",
                10,
                2,
                0,
                cuisineRepository.getOne(3),
                categoryRepository.getOne(1),
                userRepository.getOne(3),
                chaiPuddingImage
        );

        // Add the preperationSteps to the recipe and add them to the cookbook of choice
        recipe1.setPreparationStepList(preparationStepsRecipe1);
        recipe2.setPreparationStepList(preparationStepsRecipe2);
        recipe3.setPreparationStepList(preparationStepsRecipe3);

        // add recipes to cookbook
        ArrayList<Recipe> recipesInCookbook1 = new ArrayList<>(Arrays.asList(recipe1));
        ArrayList<Recipe> recipesInCookbook2 = new ArrayList<>(Arrays.asList(recipe2));
        ArrayList<Recipe> recipesInCookbook3 = new ArrayList<>(Arrays.asList(recipe3));

        if (preparationStepRepository.count() == 0 && cookbookRepository.count() == 0 &&
                recipeRepository.count() == 0) {
            Cookbook cookbook1 = new Cookbook(false, "Jasmijn's favorite's", userRepository.getOne(4));
            Cookbook cookbook2 = new Cookbook(false, "Nathalie's favorite's", userRepository.getOne(5));
            Cookbook cookbook3 = new Cookbook(false, "Jasper's favorite's", userRepository.getOne(3));

            cookbook1.setRecipes(recipesInCookbook1);
            cookbook2.setRecipes(recipesInCookbook2);
            cookbook2.setRecipes(recipesInCookbook3);
            cookbookRepository.saveAll(Arrays.asList(cookbook1, cookbook2, cookbook3));
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
            MeasuringUnit measuringUnit8 = new MeasuringUnit("kilogram", "kg");

            ArrayList<MeasuringUnit> measuringUnits = new ArrayList<>(Arrays.asList(measuringUnit1, measuringUnit2,
                    measuringUnit3, measuringUnit4, measuringUnit5, measuringUnit6, measuringUnit7, measuringUnit8));

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
            Ingredient butternutSquash = new Ingredient("butternut squash", measuringUnitRepository.getOne(8), true);
            Ingredient harissa = new Ingredient("harissa", measuringUnitRepository.getOne(7), true);
            Ingredient avocado = new Ingredient("avocado", measuringUnitRepository.getOne(1), true);
            Ingredient mixedSaled = new Ingredient("mixed salad", measuringUnitRepository.getOne(2), true);
            Ingredient mozzarella  = new Ingredient("mozzarella", measuringUnitRepository.getOne(2), true);
            Ingredient banana  = new Ingredient("banana", measuringUnitRepository.getOne(1), true);
            Ingredient vanillaSuger  = new Ingredient("vanilla suger", measuringUnitRepository.getOne(6), true);
            Ingredient chiaSeeds  = new Ingredient("chia seeds", measuringUnitRepository.getOne(7), true);

            ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(condensedMilk, casterSugar, milkChocolate, butterCookies, unsaltedButter, milk, seaSalt, fudge,
                    butternutSquash, harissa, avocado, mixedSaled, mozzarella, banana, vanillaSuger, chiaSeeds));
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
            RecipeIngredient recipeIngredient9 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(9), 1);
            RecipeIngredient recipeIngredient10 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(10), 1);
            RecipeIngredient recipeIngredient11 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(11), 2);
            RecipeIngredient recipeIngredient12 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(12), 100);
            RecipeIngredient recipeIngredient13 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(13), 125);
            RecipeIngredient recipeIngredient14 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(14), 2);
            RecipeIngredient recipeIngredient15 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(6), 500);
            RecipeIngredient recipeIngredient16 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(15), 1);
            RecipeIngredient recipeIngredient17 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(16), 6);

            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>(Arrays.asList(recipeIngredient1, recipeIngredient2, recipeIngredient3, recipeIngredient4, recipeIngredient5, recipeIngredient6, recipeIngredient7, recipeIngredient8,
                    recipeIngredient9, recipeIngredient10, recipeIngredient11, recipeIngredient12, recipeIngredient13, recipeIngredient14, recipeIngredient15, recipeIngredient16, recipeIngredient17));
            recipeIngredientRepository.saveAll(recipeIngredients);
        }
    }

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }
}