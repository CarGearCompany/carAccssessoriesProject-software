Feature: Customer Wants To Display His Purchased Products
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: customer chooses to list his purchased products
    When the customer wants to list all his purchased products
    Then the result size of the products must be 0