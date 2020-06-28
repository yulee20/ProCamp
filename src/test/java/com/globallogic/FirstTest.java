package com.globallogic;

import org.junit.jupiter.api.*;
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
        Assertions.assertEquals("Selenium", driver.findElement(By.tagName("h3")).getText());
    }
}
