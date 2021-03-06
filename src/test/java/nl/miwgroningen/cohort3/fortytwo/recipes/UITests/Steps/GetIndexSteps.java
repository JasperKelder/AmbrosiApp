package nl.miwgroningen.cohort3.fortytwo.recipes.UITests.Steps;

import io.cucumber.java.en.Then;
import nl.miwgroningen.cohort3.fortytwo.recipes.UITests.RunCucumberTest;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class GetIndexSteps {

    WebDriver driver = RunCucumberTest.driver;

    @Then("^User will see the page title$")
    public void verify_welcome_page() {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains("Eat And Treat - Overview"));
    }

}

