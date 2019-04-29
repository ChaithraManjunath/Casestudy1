
Feature: Add to cart and payment


  Scenario: cart and payment
  Given Larry creates a webdriver instance
    And opens Testme app URL
    And Login to app by giving username and password and clicks on login button
      | username    | password    |
      | lalitha     | password123 |
    And searches a product in search bar and checks is the product is same as searched product
    Then she added that product to cart and checked out for payment
   Then she redirected to payment gate way and she selected Andhrabank
    And she enters the net banking userid password and transaction id and ordered the product
      | userid      | password    |
      | 123456      | Pass@456    |
    Then she logout out and closed app