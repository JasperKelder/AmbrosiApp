package nl.miwgroningen.cohort3.fortytwo.recipes.UITests.Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.miwgroningen.cohort3.fortytwo.recipes.UITests.RunCucumberTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetUserAddRecipeSteps {

    WebDriver driver = RunCucumberTest.driver;

    @Given("^User logged in")
    public void login() {

        String actualTitleIndex = driver.getTitle();
        assertTrue(actualTitleIndex.contains("Eat And Treat - Overview"));
        driver.findElement(By.className("nav-mykitchen-container")).click();
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.click();
        username.clear();
        password.click();
        password.clear();
        username.sendKeys("reinout@smit.nl");
        password.sendKeys("password");
        driver.findElement(By.id("login-submit")).click();
        String actualTitleMyKitchen = driver.getTitle();
        assertTrue(actualTitleMyKitchen.contains("Eat And Treat - MyKitchen"));

    }

    @When("^User clicks on add Recipe")
    public void user_clicks_addrecipe_button(){

        driver.findElement(By.id("addRecipe")).click();
    }

    @And("^User fills in all the needed information and submit$")
    public void user_fills_in_recipe_info() {
        WebElement recipeTitle = driver.findElement(By.id("title"));
        WebElement preparationSteps = driver.findElement(By.id("myPreparationSteps"));
        WebElement preparationTime = driver.findElement(By.id("preparationtime"));
        WebElement servings = driver.findElement(By.id("servings"));
        WebElement cooktime = driver.findElement(By.id("cooktime"));

        recipeTitle.click(); recipeTitle.clear();
        preparationSteps.click(); preparationSteps.clear();
        preparationTime.click(); preparationTime.clear();
        servings.click(); servings.clear();
        cooktime.click(); cooktime.clear();
        recipeTitle.sendKeys("Applepie");
        preparationSteps.sendKeys("Put it in the oven");
        preparationTime.sendKeys("20");
        servings.sendKeys("8");
        cooktime.sendKeys("40");
        driver.findElement(By.id("floatrightbutton")).click();
    }

    @Then("^User will go to the index page and will see its recipe$")
    public void user_should_see_recipe_on_indexpage() {
        String actualTitleMyKitchen = driver.getTitle();
        assertTrue(actualTitleMyKitchen.contains("Eat And Treat - MyKitchen"));
        driver.findElement(By.id("nav-logo")).click();
        String actualTitleIndexPage = driver.getTitle();
        assertTrue(actualTitleIndexPage.contains("Eat And Treat - Overview"));
        assertTrue(driver.getPageSource().contains("Applepie"));
    }
}
