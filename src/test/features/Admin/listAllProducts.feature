Feature: List All Products
  Background: Shared Database
    Given Database is already filled

  Scenario: List All Products
    When the admin wants to list all products
    Then the result size of the products must be 6