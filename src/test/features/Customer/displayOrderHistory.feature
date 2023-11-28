Feature: Customer Wants To Display His Purchased Products
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: customer chooses to list his purchased products
    When the customer wants to list all his purchased products
    Then the result size of the products must be 0


  Scenario: customer chooses to list his purchased products after he purchased one
    When the customer enter  category 'interior'
    And enters  id 0
    And enters  quantity 5
    And confirms  with 'y'
    Then the result size of the products must be updated to 1

