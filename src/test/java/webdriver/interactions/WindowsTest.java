package webdriver.interactions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowsTest {

    WebDriver driver;

    @Test
    public void switchWindow(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
        driver.findElement(By.id("a-link-that-opens-a-new-window")).click();
        String currWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles=driver.getWindowHandles();
        for(String windowHandle : windowHandles){
            if(!windowHandle.equals(currWindowHandle))
                driver.switchTo().window(windowHandle);
        }
        Assert.assertEquals(driver.findElement(By.tagName("div")).getText(),"Simple page with simple test.");
        driver.switchTo().window(currWindowHandle);
        Assert.assertTrue(driver.getTitle().contains("Test page"));
    }
}
