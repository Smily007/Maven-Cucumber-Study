
Feature: Login Feature
  Scenario: Valid Login
    Given User Launch "chrome" browser
    And Open Url "https://opensource-demo.orangehrmlive.com/"
    When User Enter Email "Admin" and Password "admin123"
    And user clicks on login button.
    Then User lands on Dashboard page


 