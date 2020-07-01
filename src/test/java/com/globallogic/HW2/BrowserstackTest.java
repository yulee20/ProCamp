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
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

public class BrowserstackTest {

    WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        String USERNAME = "yuleelysenko1";
        String AUTOMATE_KEY = "7jPUcq6vrN5gUTNtHrVW";
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

//        DesiredCapabilities caps = new DesiredCapabilities();
//
//        caps.setCapability("os", "Windows");
//        caps.setCapability("os_version", "XP");
//        caps.setCapability("browser", "IE");
//        caps.setCapability("browser_version", "6");
//        caps.setCapability("name", "yuleelysenko1's First Test");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "13");
        caps.setCapability("device", "iPhone 11 Pro");
        caps.setCapability("real_mobile", "true");
        caps.setCapability("browserstack.local", "false");

        driver = new RemoteWebDriver(new URL(URL), caps);
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
