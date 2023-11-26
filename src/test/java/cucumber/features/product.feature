@smokeTest @Product
Feature: Product

  @successfullyproductDetails
  Scenario: Verify user successfully viewed product details
    And Fill in "login:usernameField" with "user_data:email"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButton"
    Then Element "home:home_header" will be displayed
    And User scrolls up until he finds element "products:productList_onesie"
    And User click "products:productList_onesie"
    Then Element "products:productList_detailOnesieTitle" will be displayed
    Then Element "products:productList_detailOnesie_desc" is equal with data "products_testData:productList_detailOnesie_desc_text"
    Then User take screenshot with file name "SauceLabsOnesie_productDetails"

  @successfullyAddProduct
  Scenario: Verify user successfully added the product to cart
    And Fill in "login:usernameField" with "user_data:email"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButton"
    Then Element "home:home_header" will be displayed
    And User scrolls up until he finds element "products:productList_onesie_addToCart"
    And User click "products:productList_onesie_addToCart"
    And User click based on coordinate "cart_testData:cartIcon_coordinate"
    Then Element "cart:cart_header" will be displayed
    Then Element "cart:cart_listOne" is equal with data "cart_testData:cart_SauceLabsOnesie"
    Then User take screenshot with file name "SuccessfullyAddProduct"