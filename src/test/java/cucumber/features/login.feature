@smokeTest @Login
Feature: Login

  @validAccount
  Scenario: Verify user will successfully log in using a valid account
    And Fill in "login:usernameField" with "user_data:email"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButton"
    Then Element "home:home_header" will be displayed
    Then User take screenshot with file name "validAccount"

  @invalidAccount
  Scenario: Verify user will not successfully sign in using an invalid account
    And Fill in "login:usernameField" with "user_data:invalid_email"
    And Fill in "login:passwordField" with "user_data:invalid_password"
    And User click "login:loginButton"
    Then Element "login:errorMessage" is equal with data "login_testData:errorMessage"
    Then Element "home:home_header" not displayed
    Then User take screenshot with file name "validAccount"