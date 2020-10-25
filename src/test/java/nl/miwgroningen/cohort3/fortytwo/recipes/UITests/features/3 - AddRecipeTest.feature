Feature: A User should be able to login and add a recipe
  Scenario: Login and create a recipe successfully
    Given User logged in
    When User clicks on add Recipe
    And User fills in all the needed information and submit
    Then User will go to the index page and will see its recipe