Feature: Add New Request
  Background: Shared Database
    Given Database is already filled

  Scenario: The Request will be added successfully
    When the admin enter the customer email 'jana@gmail.com'
    And the installer email 'hala@gmail.com'
    And the date '15/2/2024'
    And the car model 'bmw'
    And the category 'interior'
    And the product id 0
    Then the Request will be added successfully and nothing will be thrown
    And the installer request list size will be 1
    And the customer request list size will be 1
    And the date is installer schedule will be reserved 'true'

  Scenario: The Request will not be added successfully
    When the admin enters invalid customer email 'jana1@gmail.com'
    Then the Request will not be added successfully and user not found exception will be thrown

  Scenario: The Request will not be added successfully
    When the admin enters invalid installer email 'hala1@gmail.com'
    Then the Request will not be added successfully because of the wrong installer email and user not found exception will be thrown

  Scenario: The Request will not be added successfully
    When the admin enters already reserved date '8/2/2024'
    Then the Request will not be added successfully and already reserved date exception will be thrown

  Scenario: The Request will not be added successfully
    When the admin enters wrong category name 'cosmetics'
    Then the Request will not be added successfully and category noy found exception will be thrown

  Scenario: The Request will not be added successfully
    When the admin enters correct category name 'interior' and wrong product id 100
    Then the Request will not be added successfully and product not found exception exception will be thrown






