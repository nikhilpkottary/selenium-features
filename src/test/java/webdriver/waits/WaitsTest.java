package webdriver.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.cssSelector("#reveal")).click();
    }

    @Test
    public void implicitWaitTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("#revealed")).sendKeys("Testing wait");
    }

    @Test
    public void explicitWaitTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#revealed"))).sendKeys("Testing wait");
    }

    @Test
    public void fluentWaitTest() {
        WebElement revealed = driver.findElement(By.cssSelector("#revealed"));

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(9))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);

        wait.until(
                d -> {
                    revealed.sendKeys("Testing wait");
                    return true;
                });

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
