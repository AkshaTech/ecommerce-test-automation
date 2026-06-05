package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyHomePageTitle() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getPageTitle().contains("Your Store"),
            "Home page title is wrong!");
    }

    @Test
    public void verifyLogoIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoDisplayed(),
            "Logo is not displayed on home page!");
    }

    @Test
    public void verifyNavigationToLoginPage() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.getCurrentUrl().contains("route=account/login"),
            "Did not navigate to login page!");
    }
}