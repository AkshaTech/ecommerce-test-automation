package com.ecommerce.base;

import com.ecommerce.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    // WebDriver is the thing that controls the browser
    // 'protected' means test classes that extend BaseTest can access it
    protected WebDriver driver;

    // @BeforeMethod runs before EVERY test method automatically
    @BeforeMethod
    public void setUp() {

        // Read which browser to use from config.properties
        String browser = ConfigReader.getBrowser();

        // Launch the right browser based on config
        // Selenium 4 manages drivers automatically — no chromedriver.exe needed!
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        // Implicit wait — Selenium waits up to X seconds for elements to appear
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(ConfigReader.getImplicitWait())
        );

        // Page load timeout — how long to wait for a page to fully load
        driver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(ConfigReader.getPageLoadTimeout())
        );

        // Open the browser maximized — avoids element-not-visible issues
        driver.manage().window().maximize();

        // Navigate to the website
        driver.get(ConfigReader.getBaseUrl());
    }

    // @AfterMethod runs after EVERY test method automatically
    @AfterMethod
    public void tearDown() {
        // Always close the browser after each test, even if test fails
        if (driver != null) {
            driver.quit(); // quit() closes ALL windows, close() closes only current
        }
    }
}