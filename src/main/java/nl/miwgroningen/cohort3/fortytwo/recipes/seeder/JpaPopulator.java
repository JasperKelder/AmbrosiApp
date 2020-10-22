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
            Category cat1 = new Category("Breakfast");
            Category cat2 = new Category("Lunch");
            categoryRepository.save(cat1);
            categoryRepository.save(cat2);
        }
    }

    @Override
    public void seedCuisine() {
        if (cuisineRepository.count() == 0) {
            Cuisine cuisine1 = new Cuisine("Asian");
            Cuisine cuisine2 = new Cuisine("Dutch");
            cuisineRepository.save(cuisine1);
            cuisineRepository.save(cuisine2);
        }
    }

    @Override
    public void seedUser() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {

            User user1 = new User("Elmo", "MakeITWORK", "Gmail", "password");
            Role roleAdmin = new Role("ROLE_ADMIN");

            user1.setRoles(Arrays.asList(roleAdmin));

            userRepository.save(user1);

        }
    }

    @Override
    public void seedRecipeAndCookbookAndPreparationSteps() throws IOException {

        // First declare the images you want to use in the recipes
        byte[] eierbalImage = imageFromFileToByteArray("src/main/resources/static/images/newrecipes/SaltedCaramelTaart.jpg");

        // Add the preperationSteps per recipe and save them to a list
        PreparationStep preparationStep1 = new PreparationStep("Kook een ei");
        ArrayList<PreparationStep> preparationStepsRecipe1 = new ArrayList<>(Arrays.asList(preparationStep1));

        // Initialize the recipes
        Recipe recipe1 = new Recipe(
                    "Eierbal",
                    20,
                    5,
                    8,
                    cuisineRepository.getOne(1),
                    categoryRepository.getOne(1),
                    userRepository.getOne(1),
                    eierbalImage
            );

        // Add the preperationSteps to the recipe and add them to the cookbook of choice
        recipe1.setPreparationStepList(preparationStepsRecipe1);
        ArrayList<Recipe> recipesInCookbook1 = new ArrayList<>(Arrays.asList(recipe1));

        if (preparationStepRepository.count() == 0 && cookbookRepository.count() == 0 &&
                recipeRepository.count() == 0) {
            Cookbook cookbook1 = new Cookbook(false, "Jasmijn her favorite cookbook",
                    userRepository.getOne(1));

            cookbook1.setRecipes(recipesInCookbook1);
            cookbookRepository.save(cookbook1);
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

            ArrayList<MeasuringUnit> measuringUnits = new ArrayList<>(Arrays.asList(measuringUnit1, measuringUnit2,
                    measuringUnit3, measuringUnit4, measuringUnit5));

            measuringUnitRepository.saveAll(measuringUnits);
        }
    }

    @Override
    public void seedIngredient() {
        if (ingredientRepository.count() == 0) {
            Ingredient egg = new Ingredient("egg",measuringUnitRepository.getOne(4), true);
            Ingredient flower = new Ingredient("flower",measuringUnitRepository.getOne(2), true);

            ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(egg, flower));
            ingredientRepository.saveAll(ingredients);
        }
    }

    @Override
    public void seedRecipeIngredient() {
        if (recipeIngredientRepository.count() == 0) {
            RecipeIngredient recipeIngredient1 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(1), 2);

            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>(Arrays.asList(recipeIngredient1));
            recipeIngredientRepository.saveAll(recipeIngredients);
        }
    }

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }
}