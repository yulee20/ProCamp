package com.globallogic.HW1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void simpleTest() {
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium\n");
        String searchText = driver.findElement(By.tagName("h3")).getText();
        Assertions.assertTrue(searchText.contains("Selenium"));
        System.out.println("First Test Passed");
    }
}
