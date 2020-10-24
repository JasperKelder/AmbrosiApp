package nl.miwgroningen.cohort3.fortytwo.recipes.UITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features="src/test/java/nl/miwgroningen/cohort3/fortytwo/recipes/UITests/features/")
public class RunCucumberTest {

    @BeforeClass
    public static void runApplication() {
        SpringApplication.run(RecipesApplication.class);
    }
}
