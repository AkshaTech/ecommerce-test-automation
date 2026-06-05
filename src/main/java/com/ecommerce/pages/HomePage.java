package com.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // ── Locators ────────────────────────────────────────────────────
    // @FindBy is PageFactory's way of finding elements
    // It only searches for the element when you actually USE it (lazy loading)

    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    private WebElement searchButton;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountMenu;

    @FindBy(linkText = "Login")
    private WebElement loginLink;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(css = "#logo a")
    private WebElement logo;

    // ── Constructor ─────────────────────────────────────────────────
    public HomePage(WebDriver driver) {
        super(driver); // calls BasePage constructor which runs PageFactory
    }

    // ── Actions ─────────────────────────────────────────────────────
    // These methods describe WHAT a user does on this page

    public void searchForProduct(String productName) {
        type(searchBox, productName);
        click(searchButton);
    }

    public void navigateToLogin() {
        click(myAccountMenu);   // hover/click My Account
        click(loginLink);       // click Login from dropdown
    }

    public void navigateToRegister() {
        click(myAccountMenu);
        click(registerLink);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }
}