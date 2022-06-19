package com.SauceDemo.pages;

import org.openqa.selenium.By;

import static com.SauceDemo.utilities.Driver.driver;

public class LoginPage {
    public void navigateToLoginPage() {
        driver().get("https://www.saucedemo.com/");
    }

    public void fillWithPassword(String password) {
        driver().findElement(By.id("password")).sendKeys(password);
    }

    public void pressLoginButton() {
        driver().findElement(By.id("login-button")).click();
    }

    public String getUserNameIsRequiredError() {
        return driver().findElement(By.xpath("//h3")).getText();
    }

    public void fillWithUsername(String userName) {
        driver().findElement(By.id("user-name")).sendKeys(userName);
    }

    public void navigateToProductsPage() {
        driver().get("https://www.saucedemo.com/");
        fillWithUsername("standard_user");
        fillWithPassword("secret_sauce");
        pressLoginButton();
    }

}
