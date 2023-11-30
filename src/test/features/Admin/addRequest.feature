Feature: Add New Request
  Background: Shared Database
    Given Database is already filled

  Scenario: The Request will be added successfully
    When the admin enter the customer email 'jana@gmail.com'
    And the installer email 'hala@gmail.com'
    And the date '8/2/2024'
    And the car model 'bmw'
    And the category 'interior'
    And the product id 0
    Then the Request will be added successfully and nothing will be thrown
    And the installer request list size will be 1
    And the customer request list size will be 1
    And the date is installer schedule will be reserved 'true'






