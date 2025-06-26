package org.cooee.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver(); // Force Chrome only
        driver.manage().window().maximize();
        // Optional implicit wait if needed
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void launchApp(String url) {
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
