package com.globallogic.HW2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteAndSeleniumGridTest {

    WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
//        driver = new RemoteWebDriver(new URL("http://192.168.88.21:4444/wd/hub"), DesiredCapabilities.chrome());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.MAC);

//        20:48:25.637 INFO [Hub.start] - Selenium Grid hub is up and running
//        20:48:25.639 INFO [Hub.start] - Nodes should register to http://192.168.88.20:4444/grid/register/
//        20:48:25.640 INFO [Hub.start] - Clients should connect to http://192.168.88.20:4444/wd/hub
//        20:59:55.267 INFO [DefaultGridRegistry.add] - Registered a node http://192.168.88.21:18776
//        21:06:22.065 INFO [DefaultGridRegistry.add] - Registered a node http://192.168.88.19:43616

        driver = new RemoteWebDriver(new URL("http://192.168.88.20:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void simpleTest1() {
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium\n");
        String searchText = driver.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("First Test Passed");
    }

    @Test
    void simpleTest2() throws InterruptedException {
        driver.get("http://google.com");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("Selenium\n");
//        searchInput.sendKeys(Keys.RETURN);

//      explicit wait
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        String searchText = driver.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("Second Test Passed");
    }

    @Test
    void simpleTest3() throws InterruptedException {
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium\n");

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        String searchText = driver.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("Third Test Passed");
    }
}
