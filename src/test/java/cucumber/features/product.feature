@smokeTest @Product
Feature: Product

  @successfullyproductDetails
  Scenario: Verify user successfully viewed product details
    And Fill in "login:usernameField" with "user_data:email"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButton"
    Then Element "home:home_header" will be displayed
    And User scrolls up until he finds element "products:productList_onesie"
    Then User take screenshot with file name "validAccount"