package com.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // ── Locators ─────────────────────────────────────────────────────

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(css = "input[value='Login']")
    private WebElement loginButton;

    @FindBy(css = "div.alert.alert-danger")
    private WebElement errorMessage;

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgotPasswordLink;

    // ── Constructor ──────────────────────────────────────────────────
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ── Actions ──────────────────────────────────────────────────────

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    // Combines all steps — most tests will use this one method
    public void loginWith(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }
}