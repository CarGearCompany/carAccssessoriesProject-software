Feature: Customer Wants To Display Image Of A Product
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: image displays successfully
    When the customer enters category 'interior'
    And enters product id equals 1
    Then the 'Seat Cover' image


  Scenario: image doesn't display due to category not found
    When the customer enters category 'technical'
    And enters product id equals 1
    Then category not found exception will be thrown


  Scenario: image doesn't display due to product not found
    When the customer enters category 'electronic'
    And enters product id equals 8
    Then product not found exception will be thrown
