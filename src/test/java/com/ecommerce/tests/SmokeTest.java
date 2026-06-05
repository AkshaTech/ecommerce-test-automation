package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {
    // 'extends BaseTest' means setUp() and tearDown() run automatically

    @Test
    public void verifyHomePageTitle() {
        // driver comes from BaseTest — no setup needed here
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        // Assert that the title contains "Your Store"
        Assert.assertTrue(title.contains("Your Store"),
            "Home page title is wrong! Found: " + title);
    }

    @Test
    public void verifyHomePageUrl() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("awesomeqa.com"),
            "URL is wrong! Found: " + currentUrl);
    }
}