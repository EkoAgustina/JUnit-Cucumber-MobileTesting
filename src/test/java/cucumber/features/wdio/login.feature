@smokeTest @Login
Feature: Login
  @validAccount
  Scenario: User logs in with a valid account
    And User click "navbar:navLogin"
    And User click "signup:signupContain"
    And User fills in "signup:emailField" with "user_data:email"
    And User fills in "signup:passwordField" with "user_data:password"
    And User fills in "signup:repeatField" with "user_data:password"
    And User click "signup:sigupButton"
    And User click "signup:okButton"

    And User click "login:loginContain"
    And User wait 2 seconds
    Then Verify value "login:usernameField" is equal with data "user_data:email"
    Then Verify value "login:passwordField" is not equal with data "user_data:password"
    Then Verify value "login:passwordField" is equal with regex "login_testData:regexPassword"
    Then User click "login:LoginBotton"
    And User wait 2 seconds
    Then Verify value "login:succesMessage" is equal with data "login_testData:successMessage"
    Then User take screenshot with file name "Login"

