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
            Cuisine cuisine7 = new Cuisine("American");

            cuisineRepository.saveAll(Arrays.asList(cuisine1, cuisine2, cuisine3, cuisine4, cuisine5, cuisine6, cuisine7));
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
        byte[]codImage = imageFromFileToByteArray("src/main/resources/static/images/demo/4- kabeljauw-chorizo.jpg");
        byte[]cookieDoughImage = imageFromFileToByteArray("src/main/resources/static/images/demo/5- Cookie-dough-truffels.jpg");
        byte[]tunaImage = imageFromFileToByteArray("src/main/resources/static/images/demo/6- sesame tuna.jpg");

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
        PreparationStep preparationStep17 = new PreparationStep("Preheat the oven to 180 degrees.");
        PreparationStep preparationStep18 = new PreparationStep("Chop the onion and garlic fine, the cherry tomatoes in half and the chorizo into cubes.");
        PreparationStep preparationStep19 = new PreparationStep("Heat a dash of oil in a frying pan and fry the onion and garlic in it, until the onion looks translucent. Add the cherry tomatoes and season with salt and pepper. Fry for 5 minutes, until the tomatoes become soft. Add the passata and half of the fresh basil and let it simmer for about 10 minutes on low heat.");
        PreparationStep preparationStep20 = new PreparationStep("Spoon the sauce into an oven dish and spread the cod over it. Finish with the chorizo pieces and the rest of the fresh basil. ");
        PreparationStep preparationStep21 = new PreparationStep("Bake the casserole in the oven for about 10-12 minutes. Serve with the ciabatta.");

        PreparationStep preparationStep22 = new PreparationStep("Mix the butter and sugar until creamy. This can be done in a small saucepan, in which the butter can be melted over low heat so that it mixes easily.");
        PreparationStep preparationStep23 = new PreparationStep("Add the milk and mix until well incorporated.");
        PreparationStep preparationStep24 = new PreparationStep("Then add the flour and a pinch of salt and mix into a crumbly dough.");
        PreparationStep preparationStep25 = new PreparationStep("Crumble the chocolate chip cookies into chocolate chips and mix well.");
        PreparationStep preparationStep26 = new PreparationStep("Now turn 16 small balls from the dough and place them on a plate. Let the balls rest in the refrigerator for 1 hour. ");
        PreparationStep preparationStep27 = new PreparationStep("Only when the dough balls have rested in the refrigerator long enough, do you melt the chocolate. Put this au bain-marie (in a bowl over a pan of boiling water).");
        PreparationStep preparationStep28 = new PreparationStep("Remove the balls from the refrigerator and use a skewer to pass them through the melted chocolate. When you have done this, place the truffles on a parchment-lined plate.");
        PreparationStep preparationStep29 = new PreparationStep("Return the truffles to the refrigerator until the chocolate has set.");
        PreparationStep preparationStep30 = new PreparationStep("Place a large non-stick frying pan on a medium-high heat.");
        PreparationStep preparationStep31 = new PreparationStep("Rub the miso all over the tuna, then pat on the sesame seeds to cover.");
        PreparationStep preparationStep32 = new PreparationStep("Place in the hot pan with 1 tablespoon of olive oil and sear for 1½ minutes on each side, so it’s beautifully golden on the outside but blushing in the middle.");
        PreparationStep preparationStep33 = new PreparationStep("Remove to a board to rest. Quickly wipe out the pan with a ball of kitchen paper, then return to the heat.");
        PreparationStep preparationStep34 = new PreparationStep("Trim the spring onions and slice at an angle the same length as the sugar snaps, tossing both into the hot pan with a few drips of red wine vinegar and a pinch of sea salt for 2 minutes to lightly catch and char.");
        PreparationStep preparationStep35 = new PreparationStep("Slice the sesame tuna and serve on top of the veg, drizzled with 1 teaspoon of extra virgin olive oil.");

        PreparationStep preparationStep36 = new PreparationStep("");
        PreparationStep preparationStep37 = new PreparationStep("");
        PreparationStep preparationStep38 = new PreparationStep("");
        PreparationStep preparationStep39 = new PreparationStep("");
        PreparationStep preparationStep40 = new PreparationStep("");


        ArrayList<PreparationStep> preparationStepsRecipe1 = new ArrayList<>(Arrays.asList(preparationStep1, preparationStep2, preparationStep3, preparationStep4, preparationStep5, preparationStep6, preparationStep7));
        ArrayList<PreparationStep> preparationStepsRecipe2 = new ArrayList<>(Arrays.asList(preparationStep8, preparationStep9, preparationStep10,preparationStep11, preparationStep12, preparationStep13));
        ArrayList<PreparationStep> preparationStepsRecipe3 = new ArrayList<>(Arrays.asList(preparationStep14, preparationStep15, preparationStep16));
        ArrayList<PreparationStep> preparationStepsRecipe4 = new ArrayList<>(Arrays.asList(preparationStep17, preparationStep18, preparationStep19, preparationStep20, preparationStep21));
        ArrayList<PreparationStep> preparationStepsRecipe5 = new ArrayList<>(Arrays.asList(preparationStep22, preparationStep23, preparationStep24, preparationStep25, preparationStep26, preparationStep27, preparationStep28, preparationStep29));
        ArrayList<PreparationStep> preparationStepsRecipe6 = new ArrayList<>(Arrays.asList(preparationStep30, preparationStep31, preparationStep32, preparationStep33, preparationStep34, preparationStep35));

        // Initialize the recipes
        Recipe recipe1 = new Recipe("Salted caramel pie", 30, 10, 60, cuisineRepository.getOne(3), categoryRepository.getOne(5), userRepository.getOne(4), caramelpieImage);
        Recipe recipe2 = new Recipe("Squash saled", 10, 4, 50, cuisineRepository.getOne(3), categoryRepository.getOne(2), userRepository.getOne(2), squashSaladImage);
        Recipe recipe3 = new Recipe("chai pudding", 10, 2, 0, cuisineRepository.getOne(3), categoryRepository.getOne(1), userRepository.getOne(3), chaiPuddingImage);
        Recipe recipe4 = new Recipe("cod with chorizo", 10, 2, 20, cuisineRepository.getOne(1), categoryRepository.getOne(3), userRepository.getOne(5), codImage);
        Recipe recipe5 = new Recipe("cookie dough truffels", 20, 16, 100, cuisineRepository.getOne(7), categoryRepository.getOne(4), userRepository.getOne(5), cookieDoughImage);
        Recipe recipe6 = new Recipe("Tuna saled", 0, 2, 10, cuisineRepository.getOne(4), categoryRepository.getOne(2), userRepository.getOne(4), tunaImage);

        // Add the preperationSteps to the recipe and add them to the cookbook of choice
        recipe1.setPreparationStepList(preparationStepsRecipe1);
        recipe2.setPreparationStepList(preparationStepsRecipe2);
        recipe3.setPreparationStepList(preparationStepsRecipe3);
        recipe4.setPreparationStepList(preparationStepsRecipe4);
        recipe5.setPreparationStepList(preparationStepsRecipe5);
        recipe6.setPreparationStepList(preparationStepsRecipe6);

        // add recipes to cookbook
        ArrayList<Recipe> recipesInCookbook1 = new ArrayList<>(Arrays.asList(recipe1, recipe6));
        ArrayList<Recipe> recipesInCookbook2 = new ArrayList<>(Arrays.asList(recipe2, recipe5));
        ArrayList<Recipe> recipesInCookbook3 = new ArrayList<>(Arrays.asList(recipe3));
        ArrayList<Recipe> recipesInCookbook4 = new ArrayList<>(Arrays.asList(recipe4));

        if (preparationStepRepository.count() == 0 && cookbookRepository.count() == 0 &&
                recipeRepository.count() == 0) {
            Cookbook cookbook1 = new Cookbook(false, "Jasmijn's favorite's", userRepository.getOne(4));
            Cookbook cookbook2 = new Cookbook(false, "Nathalie's favorite's", userRepository.getOne(5));
            Cookbook cookbook3 = new Cookbook(false, "Jasper's favorite's", userRepository.getOne(3));
            Cookbook cookbook4 = new Cookbook(false, "Reinout's favorite's", userRepository.getOne(2));

            cookbook1.setRecipes(recipesInCookbook1);
            cookbook2.setRecipes(recipesInCookbook2);
            cookbook3.setRecipes(recipesInCookbook3);
            cookbook4.setRecipes(recipesInCookbook4);
            cookbookRepository.saveAll(Arrays.asList(cookbook1, cookbook2, cookbook3, cookbook4));
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
            Ingredient onion  = new Ingredient("onion", measuringUnitRepository.getOne(1), true);
            Ingredient garlic  = new Ingredient("garlic", measuringUnitRepository.getOne(1), true);
            Ingredient chorizo  = new Ingredient("chorizo", measuringUnitRepository.getOne(2), true);
            Ingredient passata  = new Ingredient("passata", measuringUnitRepository.getOne(2), true);
            Ingredient cod  = new Ingredient("cod", measuringUnitRepository.getOne(2), true);
            Ingredient ciabatta  = new Ingredient("ciabatta bread", measuringUnitRepository.getOne(1), true);
            Ingredient butter  = new Ingredient("butter", measuringUnitRepository.getOne(2), true);
            Ingredient flower  = new Ingredient("flower", measuringUnitRepository.getOne(2), true);
            Ingredient chocolateCookie  = new Ingredient("chocolate cookie", measuringUnitRepository.getOne(2), true);
            Ingredient sugar  = new Ingredient("sugar", measuringUnitRepository.getOne(2), true);
            Ingredient misoPaste  = new Ingredient("miso paste", measuringUnitRepository.getOne(7), true);
            Ingredient tuna  = new Ingredient("tuna steaks", measuringUnitRepository.getOne(2), true);
            Ingredient sesameSeeds  = new Ingredient("sesame seeds", measuringUnitRepository.getOne(7), true);
            Ingredient springOnion  = new Ingredient("spring onions", measuringUnitRepository.getOne(1), true);
            Ingredient sugersnapPeas = new Ingredient("sugersnap peas", measuringUnitRepository.getOne(2), true);

            ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(condensedMilk, casterSugar, milkChocolate, butterCookies, unsaltedButter, milk, seaSalt, fudge,
                    butternutSquash, harissa, avocado, mixedSaled, mozzarella, banana, vanillaSuger, chiaSeeds, onion, garlic, chorizo, passata, cod, ciabatta, butter, flower, chocolateCookie,
                    sugar, misoPaste, tuna, sesameSeeds, springOnion, sugersnapPeas));
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
            RecipeIngredient recipeIngredient18 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(17), 1);
            RecipeIngredient recipeIngredient19 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(18), 2);
            RecipeIngredient recipeIngredient20 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(19), 250);
            RecipeIngredient recipeIngredient21 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(20), 275);
            RecipeIngredient recipeIngredient22 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(21), 260);
            RecipeIngredient recipeIngredient23 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(22), 1);

            //todo fix: ingredienten worden bij recept 2 wseer gegeven en ingredienten van recept 2 bij recept 5.
            RecipeIngredient recipeIngredient24 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(23), 100);
            RecipeIngredient recipeIngredient25 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(24), 225);
            RecipeIngredient recipeIngredient26 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(6), 30);
            RecipeIngredient recipeIngredient27 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(25), 100);
            RecipeIngredient recipeIngredient28 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(26), 60);
            RecipeIngredient recipeIngredient29 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(2), 115);
            RecipeIngredient recipeIngredient30 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(3), 200);

            RecipeIngredient recipeIngredient31 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(27), 1);
            RecipeIngredient recipeIngredient32 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(28), 150);
            RecipeIngredient recipeIngredient33 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(29), 4);
            RecipeIngredient recipeIngredient34 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(30), 8);
            RecipeIngredient recipeIngredient35 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(31), 150);

            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>(Arrays.asList(recipeIngredient1, recipeIngredient2, recipeIngredient3, recipeIngredient4, recipeIngredient5, recipeIngredient6, recipeIngredient7, recipeIngredient8,
                    recipeIngredient9, recipeIngredient10, recipeIngredient11, recipeIngredient12, recipeIngredient13, recipeIngredient14, recipeIngredient15, recipeIngredient16, recipeIngredient17, recipeIngredient18,
                    recipeIngredient19, recipeIngredient20, recipeIngredient21, recipeIngredient22, recipeIngredient23, recipeIngredient24, recipeIngredient25, recipeIngredient26, recipeIngredient27, recipeIngredient28,
                    recipeIngredient29, recipeIngredient30, recipeIngredient31, recipeIngredient32, recipeIngredient33, recipeIngredient34, recipeIngredient35));
            recipeIngredientRepository.saveAll(recipeIngredients);
        }
    }

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }
}