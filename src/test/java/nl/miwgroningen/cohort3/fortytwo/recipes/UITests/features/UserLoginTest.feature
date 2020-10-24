Feature: A User should be able to login
  Scenario: Go to the login page and login successfully
      And Index screen will be displayed
    When User clicks on login
    And User fill in its credentials to the form
    Then User will see the myrecipe page