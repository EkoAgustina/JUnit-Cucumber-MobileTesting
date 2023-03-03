@smokeTest @signup
Feature: Sign up
  @validSignup
  Scenario: User successfully Sign up
    And User click "navbar:navLogin"
    And User click "signup:signupContain"
    And Fill in "signup:emailField" with "user_data:email"
    And Fill in "signup:passwordField" with "user_data:password"
    And Fill in "signup:repeatField" with "user_data:password"
    And User click "signup:sigupButton"
    And Wait 2 seconds
    Then Element "signup:parentPanel" will be displayed
    Then Element "signup:panelMessage" is equal with data "signup_testData:successMessage"
    Then User take screenshot with file name "SignUp"