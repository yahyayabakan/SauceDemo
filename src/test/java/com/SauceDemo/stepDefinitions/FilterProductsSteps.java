package com.SauceDemo.stepDefinitions;

import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilterProductsSteps {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @When("I click on the {string} dropdown option")
    public void i_click_on_the_dropdown_option(String visibleText) {
        productsPage.selectMenu(visibleText);
    }

    @Then("The products should be displayed alphabetically, in {string}")
    public void the_products_should_be_displayed_alphabetically_in(String orderType) {
        assertTrue(productsPage.validateName(orderType));
    }

    @Given("I am on the Products page")
    public void i_am_on_the_products_page() {
        loginPage.navigateToProductsPage();
    }

    @When("I click on the filter dropdown")
    public void i_click_on_the_filter_dropdown() {
        productsPage.clickFilterDropdown();
    }

    @Then("The filter option should display {string}")
    public void the_filter_option_should_display(String currentOption) {
        assertEquals(currentOption, productsPage.getCurrentFilter());
    }

    @Then("The products should be displayed by price, in {string}")
    public void the_products_should_be_displayed_by_price_in(String orderType) {
        assertTrue(productsPage.validatePrice(orderType));
    }
}
