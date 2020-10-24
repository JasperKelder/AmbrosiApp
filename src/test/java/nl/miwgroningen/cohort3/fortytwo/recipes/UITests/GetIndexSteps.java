package nl.miwgroningen.cohort3.fortytwo.recipes.UITests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

public class GetIndexSteps {

    WebDriver driver;

    @Given("^Open the Chrome and launch the application$")
    public void open_the_chrome_and_launch_the_application() throws Throwable {
        System.setProperty("webdriver.chrome.driver","src/test/java/nl/miwgroningen/cohort3/fortytwo/recipes/UITests/features/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
    }

    @Then("^User will see the page title$")
    public void verify_welcome_page() throws Throwable {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains("EatAndTreat - Overview"));
        driver.close();
    }

}

