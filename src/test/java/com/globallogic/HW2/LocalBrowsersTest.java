package com.globallogic.HW2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalBrowsersTest {

    WebDriver driver1, driver2, driver3;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.setCapability("acceptInsecureCerts", true);
        options.setExperimentalOption("w3c", false);

        driver1 = new ChromeDriver(options);

        driver2 = new FirefoxDriver();
        driver3 = new SafariDriver();

//        read capabilities for driver1
        System.out.println(((HasCapabilities) driver1).getCapabilities());
    }

    @AfterEach
    void tearDown() {
        driver1.quit();
        driver2.quit();
        driver3.quit();
    }

    @Test
    void simpleTest1() {
        driver1.get("http://google.com");
        driver1.findElement(By.name("q")).sendKeys("Selenium\n");
        String searchText = driver1.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("Chrome Test Passed");
    }

    @Test
    void simpleTest2() throws InterruptedException {
        driver2.get("http://google.com");

//      implicit wait
//      driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement searchInput = driver2.findElement(By.name("q"));
        searchInput.sendKeys("Selenium\n");
        searchInput.sendKeys(Keys.RETURN);

//      explicit wait
        WebDriverWait wait = new WebDriverWait(driver2,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        String searchText = driver2.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("FireFox Test Passed");
    }

    @Test
    void simpleTest3() throws InterruptedException {
        driver3.get("http://google.com");
        driver3.manage().window().maximize();
        driver3.findElement(By.name("q")).sendKeys("Selenium\n");

        //      explicit wait
        WebDriverWait wait = new WebDriverWait(driver3,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        String searchText = driver3.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("Safari Test Passed");
    }
}
