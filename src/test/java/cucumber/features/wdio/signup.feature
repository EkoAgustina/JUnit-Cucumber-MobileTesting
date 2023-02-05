@smokeTest @signup
Feature: Sign up
  @validSignup
  Scenario: User successfully Sign up
    And User click "navbar:navLogin"
    And User click "signup:signupContain"
    And User fills in "signup:emailField" with "user_data:email"
    And User fills in "signup:passwordField" with "user_data:password"
    And User fills in "signup:repeatField" with "user_data:password"
    And User click "signup:sigupButton"
    And User wait 2 seconds
    Then Verify element "signup:parentPanel" will be displayed
    Then Verify value "signup:panelMessage" is equal with data "signup_testData:successMessage"
    Then User take screenshot with file name "SignUp"