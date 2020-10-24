package nl.miwgroningen.cohort3.fortytwo.recipes.UITests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class GetUserLoginSteps {

    WebDriver driver = RunCucumberTest.driver;

    @And("^Index screen will be displayed$")
    public void verify_welcome_page() {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains("EatAndTreat - Overview"));
    }

    @When("^User clicks on login$")
    public void user_clicks_login_button(){
            driver.findElement(By.className("nav-mykitchen-container")).click();
    }

    @And("^User fill in its credentials to the form$")
    public void user_fills_in_credentials() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.click();
        username.clear();
        password.click();
        password.clear();
        username.sendKeys("reinout@smit.nl");
        password.sendKeys("password");
        driver.findElement(By.id("login-submit")).click();
    }

    @Then("^User will see the myrecipe page$")
    public void user_should_see_myrecipe_page() {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains("EatAndTreat - MyKitchen"));
        driver.close();
    }

}
