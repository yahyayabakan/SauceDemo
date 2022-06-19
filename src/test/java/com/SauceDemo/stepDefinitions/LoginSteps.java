package com.SauceDemo.stepDefinitions;

import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("I am on the Login page")
    public void i_am_on_the_login_page() {
        loginPage.navigateToLoginPage();
    }

    @When("I type in a valid password in the password field")
    public void i_type_in_a_valid_password_in_the_password_field() {
        loginPage.fillWithPassword("secret_sauce");
    }

    @When("I press the Login button")
    public void i_press_the_login_button() {
        loginPage.pressLoginButton();
    }

    @Then("I should see a {string} error message")
    public void i_should_see_a_error_message(String error) {
        assertTrue(error.equals(loginPage.getUserNameIsRequiredError()));
    }

    @When("I type in a valid username in the username field")
    public void i_type_in_a_valid_username_in_the_username_field() {
        loginPage.fillWithUsername("standard_user");
    }


    @Then("I should login successfully")
    public void i_should_login_successfully() {
        assertEquals("https://www.saucedemo.com/inventory.html", productsPage.getPageURL());
    }

    @Then("I should see an error message about the account being locked")
    public void i_should_see_an_error_message_about_the_account_being_locked() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("The user account is locked")
    public void the_user_account_is_locked() {
        loginPage.fillWithUsername("locked_out_user");
    }

    @When("I type in an invalid password in the password field")
    public void i_type_in_an_invalid_password_in_the_password_field() {
        loginPage.fillWithPassword("invalid_pass");
    }

    @Then("I should see an error message about invalid credentials")
    public void i_should_see_an_error_message_about_invalid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("I type in an invalid username in the username field")
    public void i_type_in_an_invalid_username_in_the_username_field() {
        loginPage.fillWithUsername("invalid_user");
    }


}
