package nl.miwgroningen.cohort3.fortytwo.recipes.UITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.SpringApplication;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features="src/test/java/nl/miwgroningen/cohort3/fortytwo/recipes/UITests/features/")
public class RunCucumberTest {

    protected static WebDriver driver;
    public static String runWhere;
    private static ChromeOptions chromeOptions;

    @BeforeClass
    public static void runApplication() {
        SpringApplication.run(RecipesApplication.class);
    }


    @BeforeClass
    public static void setUp() throws MalformedURLException{

        chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        getEnvironment();
    }

    private static void getEnvironment() throws MalformedURLException {
        runWhere = System.getenv("RUNWHERE");

        if (runWhere.equals("local")) {
            // Standard local visual test call
            driver = new ChromeDriver();
        }
        else if (runWhere.equals("pipeline")) {
            // build server headless chrome CI/CD example
            chromeOptions.addArguments("--headless", "--no-sandbox");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (runWhere.equals("container")) {
            // selenium hub remote settings (container based CI/CD)
            String Selenium = "http://selenium_hub:4444/wd/hub";
            driver = new RemoteWebDriver(new URL(Selenium), chromeOptions);
        };
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
