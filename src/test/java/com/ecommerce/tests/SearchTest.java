package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
    }

    // ── Test 1: Search for existing product ─────────────────────────
    @Test
    public void testSearchExistingProduct() {
        homePage.searchForProduct("MacBook");

        Assert.assertTrue(
            homePage.getCurrentUrl().contains("search=MacBook"),
            "Search did not work!"
        );
    }

    // ── Test 2: Search for non-existing product ──────────────────────
    @Test
    public void testSearchNonExistingProduct() {
        homePage.searchForProduct("xyzproductnotexist123");

        Assert.assertTrue(
            driver.getPageSource().contains("There is no product that matches the search criteria"),
            "No-results message not shown!"
        );
    }

    // ── Test 3: Search with empty field ─────────────────────────────
    @Test
    public void testSearchWithEmptyField() {
        homePage.searchForProduct("");

        Assert.assertTrue(
            driver.getPageSource().contains("There is no product that matches the search criteria"),
            "Empty search should show no results!"
        );
    }
}