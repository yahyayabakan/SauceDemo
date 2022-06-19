package com.SauceDemo.stepDefinitions;

import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductsSteps {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("I am on the products page")
    public void i_am_on_the_products_page() {
        loginPage.navigateToProductsPage();
    }

    @When("user adds {string} product to cart")
    public void user_adds_product_to_cart(String item) {
        productsPage.addToCart(item);
    }

    @When("user clicks cart button")
    public void user_clicks_cart_button() {
        productsPage.clickCart();
    }

    @Then("{string} product  should be listed with {string} correct price")
    public void product_should_be_listed_with_correct_price(String item, String price) {
        assertTrue(productsPage.getPrice(item).equals(price));
    }

    @Then("user clicks to continue shopping button and navigates back to the products page")
    public void user_clicks_to_continue_shopping_button_and_navigates_back_to_the_products_page() {
        productsPage.clickContinueShoppingButton();
    }

    @Then("user clicks checkout button")
    public void user_clicks_checkout_button() {
        productsPage.clickCheckoutButton();
    }

    @Then("user populates First Name {string}, Last Name {string} and ZipPostal Code {string}")
    public void user_populates_first_name_last_name_and_zip_postal_code(String firstName, String lastName, String postalCode) {
        productsPage.populateInformation(firstName, lastName, postalCode);
    }

    @Then("user clicks continue button")
    public void user_clicks_continue_button() {
        productsPage.continueCheckoutClick();
    }

    @Then("total price of items in cart should be correct")
    public void total_price_of_items_in_cart_should_be_correct() {
        assertTrue(productsPage.calculateTotalPrice() == productsPage.readTotalLabel());
    }

    @Then("user clicks finish button and receives {string} notification")
    public void user_clicks_finish_button_and_receives_notification(String string) {
        productsPage.clickFinishButton();
        assertEquals(productsPage.orderConfirmationMessage.getText(), "THANK YOU FOR YOUR ORDER");
    }
}
