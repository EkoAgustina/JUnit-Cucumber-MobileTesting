@smokeTest @signup
Feature: Sign up
  @validSignup
  Scenario: User successfully Sign up
    And User click "navbar.yml:navLogin"
    And User click "signup.yml:signupContain"
    And User fills in "signup.yml:emailField" with "user_data.yml:email"
    And User fills in "signup.yml:passwordField" with "user_data.yml:password"
    And User fills in "signup.yml:repeatField" with "user_data.yml:password"
    And User click "signup.yml:sigupButton"