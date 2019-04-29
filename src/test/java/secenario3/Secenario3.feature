Feature: Adding new product 
	Alex as a admin add new product to testme app


  Scenario: Add product as admin
    Given larry creates a webdriver instance
    And she opens Test me app URL
    And Login to application by giving admin username and password and clicks on login button
      | username  | password    |
      | Admin     | password456 | 
    Then click on Add product selects category as "Electronics" and subcategory as "Head Phone" 
    And give productname "Headset" price 1000 quantity 20 brand "BOAT" Description "Headphone suitable for office" 
    And comebacks to home page and logout from admin page
     Then it navigated to Application home page 
