package webdriver.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {

    WebDriver driver;

    @BeforeMethod
    public void initializeWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void implicitWaitTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.cssSelector("#reveal")).click();
        driver.findElement(By.cssSelector("#revealed")).sendKeys("Testing wait");
    }

    @AfterMethod
    public void quitWebDriver() {
        driver.quit();
    }
}
