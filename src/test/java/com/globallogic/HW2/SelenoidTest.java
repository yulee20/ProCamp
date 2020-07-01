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
import java.net.URI;
import java.net.URL;

public class SelenoidTest {

    WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("chrome");
//        capabilities.setVersion("81.0");
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("77.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        driver = new RemoteWebDriver(
                URI.create("http://192.168.88.20:4444/wd/hub").toURL(),
                capabilities
        );
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
//        for FireFox browser
        searchInput.sendKeys(Keys.RETURN);

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
