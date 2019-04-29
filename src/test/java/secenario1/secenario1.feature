Feature: Adding new product 
	Alex as a admin add new product to testme app


  Scenario: Add product as admin
    Given Alex creates a webdriver instance
    And opens Test me app URL
    And Login to app by giving admin username and password and clicks on login button
      | username  | password    |
      | Admin     | password456 |
    And Click on Add Categories and fills mandatory details and add the category
     | categoryname   | Description    |
     | Footware       | Abc            |
   And  Click on Add SubCategories and fills mandatory details and add the Subcategory
    | Subcategoryname | Description    |
    |Mens             | def            |
    Then click on Add product and fills mandatory details and add the product
    And comeback to home page and logout from admin page
     Then it navigated to App home page 
    And he checks product is present or not
     
     
