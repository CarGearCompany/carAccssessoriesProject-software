Feature: Customer Wants To Purchase Products
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: Customer purchases successfully
    When the customer enter category 'interior'
    And enters id 0
    And enters quantity 5
    And confirms with 'y'
    Then the transaction is done succssfully and the pruchasedProducts list size will be 1 and quantity of that product will be 10



  Scenario: Customer fails to purchase due to product not found exception
    When the customer enter category 'exterior'
    And enters id 5
    And enters quantity 1
    And confirms with 'Y'
    Then the transaction fails and product not found exception will be thrown

  Scenario: Customer fails to purchase due to category not found exception
    When the customer enter category 'cosmetics'
    And enters id 5
    And enters quantity 1
    And confirms with 'Y'
    Then the transaction fails and category not found exception will be thrown


  Scenario: Customer doesn't confirm the purchase
    When the customer enter category 'exterior'
    And enters id 2
    And enters quantity 1
    And confirms with 'n'
    Then the transaction is not completed and purchase not confirmed exception will be thrown



  Scenario: Customer tries to purchase an out of stock product
    When the customer enter category 'electronic'
    And enters id 5
    And enters quantity 1
    And confirms with 'y'
    Then the transaction is not done and out of stock exception will be thrown


  Scenario: Customer tries to purchase a product by a quantity bigger than its available one
    When the customer enter category 'exterior'
    And enters id 3
    And enters quantity 9
    And confirms with 'y'
    Then the transaction is not done and not enough items available exception will be thrown


