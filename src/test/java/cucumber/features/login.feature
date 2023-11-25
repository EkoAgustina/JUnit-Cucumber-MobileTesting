@smokeTest @Login
Feature: Login

  @validAccount
  Scenario: Verify user will successfully log in using a valid account
    And Fill in "login:usernameField" with "user_data:email"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButton"
    Then Element "home:home_header" will be displayed
    Then User take screenshot with file name "validAccount"
