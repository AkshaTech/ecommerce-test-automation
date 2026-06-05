package com.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    // Every page needs access to the driver
    protected WebDriver driver;

    // WebDriverWait is smarter than implicit wait
    // It waits for a SPECIFIC condition (element visible, clickable etc)
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        // Wait up to 10 seconds for conditions
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // PageFactory initializes all @FindBy elements in the page class
        PageFactory.initElements(driver, this);
    }

    // ── Reusable helper methods every page can use ──────────────────

    // Wait until element is visible then click it
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Wait until element is visible then type into it
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();          // clear any existing text first
        element.sendKeys(text);
    }

    // Get text from an element
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // Check if element is displayed on the page
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Get the current page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Get the current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}