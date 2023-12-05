Feature: Customer Wants To Display All Product
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: customer wants to list all products
    When the customer wants to list all products
    Then the result size of the products will be 6