Feature: Installer Wants To Display His installation Requests
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'hala@gmail.com' and 'Hala@123'


  Scenario: installer chooses to list his installation Requests
    When the installer wants to list all his installation Requests
    Then the result size of the installation Requests is 0

  Scenario: installer chooses to list his installation Requests
    When the installer with 'hala@gmail.com' gets a request from the customer who logged with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'
    And the car model is 'bmw'
    And the date is valid date '15/2/2024'
    And the category is 'interior'
    And the product id is 0
    Then the result size of the installation Requests must be 1