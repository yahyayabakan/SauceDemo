@ui @addToCart
Feature:  Add to shopping cart

  Background: I am logged in.
    Given I am on the products page

  Scenario: As a user, I want to add one item to the shopping cart
    When  user adds "Sauce Labs Onesie" product to cart
    And user clicks cart button
    Then "Sauce Labs Onesie" product  should be listed with "$7.99" correct price
    And user clicks checkout button
    And user populates First Name "John", Last Name "Lock" and ZipPostal Code "NE83PU"
    And user clicks continue button
    Then total price of items in cart should be correct
    And user clicks finish button and receives "THANK YOU FOR YOUR ORDER" notification

  Scenario: As a user, I want to add multiple items to the shopping cart

    When  user adds "Sauce Labs Backpack" product to cart
    And user clicks cart button
    Then "Sauce Labs Backpack" product  should be listed with "$29.99" correct price
    And user clicks to continue shopping button and navigates back to the products page
    And user adds "Sauce Labs Bike Light" product to cart
    And user clicks cart button
    And user clicks checkout button
    And user populates First Name "John", Last Name "Lock" and ZipPostal Code "NE83PU"
    And user clicks continue button
    Then total price of items in cart should be correct
    And user clicks finish button and receives "THANK YOU FOR YOUR ORDER" notification