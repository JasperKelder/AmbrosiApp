package nl.miwgroningen.cohort3.fortytwo.recipes.UITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features="src/test/java/nl/miwgroningen/cohort3/fortytwo/recipes/UITests/features")
public class RunCucumberTest {

    public static WebDriver driver;
    public static ChromeOptions chromeOptions;

    @BeforeClass
    public static void runApplication() {
        SpringApplication.run(RecipesApplication.class);
        System.setProperty("webdriver.chrome.driver","src/test/java/nl/miwgroningen/cohort3/fortytwo/recipes/UITests/features/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--headless", "--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
    }

    @AfterClass
    public static void quitDriver() {
        driver.close();
        driver.quit();
    }
}
