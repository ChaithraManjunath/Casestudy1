
Feature: Title of your feature
  I want to use this template for my feature file


  Scenario: Title of your scenario
    Given larry  create a webdriver instance
    And she opened Test me app URL
    And She enter valid credentials and logged into admin page
      | username  | password    |
      | Admin     | password456 | 
    Then she added category as "Chai" and subcategory as "Chaithra" 
    And give productname  price 1000 quantity 10 brand "Adidas" Description "Verygood"   
     | PN  |
      | M_Ball| 
      | M_Bat| 
     And she logout from admin page
     Then page navigated to Application home page

 # Scenario Outline: Title of your scenario outline
  #  Given chcek the the app is logined by admin or user <page>

   # Examples: 
   #   | page  |
     # | Admin Home |
    #  | Home |
