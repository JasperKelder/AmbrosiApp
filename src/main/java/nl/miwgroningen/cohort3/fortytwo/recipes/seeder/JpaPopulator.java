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

    @Override
    public void run(String... args) throws Exception {
        seedCategory();
        seedCuisine();
        seedUser();
        seedRole();
        seedRecipe();
        seedCookbook();
        seedMeasuringUnit();
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
        if (userRepository.count() == 0) {
            User user1 = new User("Elmo", "Make IT Work", "42@gmail.com",
                    "$2a$10$VRCtIl4CVgV5n9CspNQhkOMpz8KrfND5fiGUwlXTKsWSO99zRboqm");
            userRepository.save(user1);
        }
    }

    @Override
    public void seedRole() {
        if (roleRepository.count() == 0) {
            Role roleUser = new Role("ROLE_USER");
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
        }
    }

    @Override
    public void seedRecipe() throws IOException {
        byte[] eierbalImage = imageFromFileToByteArray("src/main/resources/static/images/newrecipes/SaltedCaramelTaart.jpg");
        if (recipeRepository.count() == 0) {
            Recipe recipe1 = new Recipe(
                    "Eierbal",
                    "Men neme een eierbal en stopt hem in de frituur",
                    20,
                    5,
                    8,
                    cuisineRepository.getOne(1),
                    categoryRepository.getOne(1),
                    userRepository.getOne(1),
                    eierbalImage
            );
            recipeRepository.save(recipe1);
        }
    }

    @Override
    public void seedCookbook() {
        if (cookbookRepository.count() == 0) {
            Cookbook cookbook1 = new Cookbook(false, "Jasmijn her favorite cookbook", userRepository.getOne(1));
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

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }
}