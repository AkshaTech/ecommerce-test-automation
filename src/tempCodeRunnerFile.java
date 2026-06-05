package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    // Navigate to login page before each test
    @BeforeMethod
    public void navigateToLogin() {
        homePage = new HomePage(driver);
        homePage.navigateToLogin();
        loginPage = new LoginPage(driver);
    }

    // ── Test 1: Valid login ──────────────────────────────────────────
    @Test
    public void testValidLogin() {
        loginPage.loginWith(
            ConfigReader.getValidEmail(),
            ConfigReader.getValidPassword()
        );

        // After successful login, URL changes to account page
        Assert.assertTrue(
            loginPage.getCurrentUrl().contains("route=account/account"),
            "Valid login failed — not redirected to account page!"
        );
    }

    // ── Test 2: Wrong password ───────────────────────────────────────
    @Test
    public void testLoginWithWrongPassword() {
        loginPage.loginWith(
            ConfigReader.getValidEmail(),
            ConfigReader.getInvalidPassword()
        );

        // Should stay on login page and show error
        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Error message not shown for wrong password!"
        );
    }

    // ── Test 3: Wrong email ──────────────────────────────────────────
    @Test
    public void testLoginWithWrongEmail() {
        loginPage.loginWith(
            ConfigReader.getInvalidEmail(),
            ConfigReader.getValidPassword()
        );

        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Error message not shown for wrong email!"
        );
    }

    // ── Test 4: Empty fields ─────────────────────────────────────────
    @Test
    public void testLoginWithEmptyFields() {
        loginPage.loginWith("", "");

        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Error message not shown for empty fields!"
        );
    }

    // ── Test 5: Error message text ───────────────────────────────────
    @Test
    public void testErrorMessageText() {
        loginPage.loginWith(
            ConfigReader.getInvalidEmail(),
            ConfigReader.getInvalidPassword()
        );

        String errorText = loginPage.getErrorMessage();
        System.out.println("Error message: " + errorText);

        Assert.assertTrue(
            errorText.contains("No match for E-Mail Address and/or Password"),
            "Error message text is wrong! Found: " + errorText
        );
    }
}