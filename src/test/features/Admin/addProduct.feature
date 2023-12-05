Feature: Add New Product
  Background: Shared Database
    Given Database is already filled

  Scenario: The product will be added successfully
    When the admin enter the name 'interior' of the category
    And the product id is valid and equals 2
    Then the product will be added successfully and nothing will be thrown

  Scenario: The product won't be added due to non-existing category exception
    When the admin enters invalid name 'cosmetics' of the category
    And the product id is valid and equals 2
    Then the product will not be added and category not found exception will be thrown


  Scenario: The product won't be added due to already-existing product exception
    When the admin enter the name 'interior' of the category
    And the product id is already exists and equals 1
    Then the product will not be added and product already exist exception will be thrown