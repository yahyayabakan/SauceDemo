package com.SauceDemo.pages;

import io.cucumber.java.eo.Do;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.SauceDemo.utilities.Driver.driver;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(driver(), this);
    }

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    @FindBy(xpath = "//button[@name='continue-shopping']")
    private WebElement continueShopping;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueCheckout;

    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    public WebElement orderConfirmationMessage;


    public String getPageURL() {
        return driver().getCurrentUrl();
    }

    public void addToCart(String item) {
        driver().findElement(By.xpath("//*[contains(text(), '" + item + "')]//parent::a//parent::div//parent::div//div[2]//button")).click();
    }

    public void clickFilterDropdown() {
        driver().findElement(By.className("select_container")).click();
    }

    public String getCurrentFilter() {
        return driver().findElement(By.className("active_option")).getText();
    }

    public void selectMenu(String visibleText) {
        Select select = new Select(driver().findElement(By.className("product_sort_container")));
        select.selectByVisibleText(visibleText);
    }

    public void clickCart() {
        cart.click();
    }

    public String getPrice(String item) {
        return driver().findElement(By.xpath("//div[contains(text(),'" + item + "')]//parent::a/following-sibling::div[2]/div")).getText();
    }

    public void clickContinueShoppingButton() {
        continueShopping.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void populateInformation(String firstName, String lastName, String postalCode) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
    }

    public void continueCheckoutClick() {
        continueCheckout.click();
    }

    public double calculateTotalPrice() {
        List<WebElement> elements = driver().findElements(By.className("inventory_item_price"));
        double totalPrice = 0;
        for (WebElement element : elements) {
            totalPrice += Double.parseDouble(element.getText().substring(1));
        }
        double tax = Double.parseDouble(driver().findElement(By.className("summary_tax_label")).getText().substring(6));
        return totalPrice + tax;
    }

    public double readTotalLabel() {
        return Double.parseDouble(totalPrice.getText().substring(8));
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public boolean validateName(String nameOrder) {
        boolean name = true;
        List<WebElement> products = driver().findElements(By.className("inventory_item_name"));
        for (int i = 0; i < products.size(); i++) {
            if (i == products.size() - 1) break;
            if (nameOrder.equals("AtoZ")) {
                if (products.get(i).getText().compareTo(products.get(i + 1).getText()) < 0) continue;
                else name = false;
            } else if (nameOrder.equals("ZtoA")) {
                if (products.get(i).getText().compareTo(products.get(i + 1).getText()) > 0) continue;
                else name = false;
            }
        }
        return name;
    }

    public boolean validatePrice(String priceOrder) {
        boolean price = true;
        List<WebElement> products = driver().findElements(By.className("inventory_item_price"));
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getText());
            if (i == products.size() - 1) break;
            if (priceOrder.equals("descending order")) {
                if (Double.parseDouble(products.get(i).getText().substring(1)) >= Double.parseDouble(products.get(i + 1).getText().substring(1)))
                    continue;
                else price = false;
            } else if (priceOrder.equals("ascending order")) {
                if (Double.parseDouble(products.get(i).getText().substring(1)) <= Double.parseDouble(products.get(i + 1).getText().substring(1)))
                    continue;
                else price = false;
            }
        }
        return price;
    }


}

