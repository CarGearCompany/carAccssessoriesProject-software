Feature: Edit Requests
  Background: Shared Database
    Given Database is already filled

  Scenario: request failed to edit because installer not found exception
    When the installer email is 'flan@gmail.com'
    Then the request will not be updated and user not found will be thrown

  Scenario: request failed to edit because invalid email format exception
    When the invalid format installer email is 'flan@gmail'
    Then the request will not be updated and invalid email format will be thrown

  Scenario: request failed to edit because request not found
    When the installer email is 'hala@gmail.com'
    And the date is '5/12/4510'
    Then the request will not be updated and item not found will be thrown

  Scenario: request failed to edit because already reserved date
    When the installer email is 'hala@gmail.com'
    And the date is '8/2/2024'
    And the edit field is 'date'
    And the new value is '15/2/2024'
    Then the request will not be updated and already reserved date will be thrown

  Scenario: request failed to edit because category not found
    When the category name is 'cat1'
    Then the request will not be updated and category not found will be thrown

  Scenario: request failed to edit because product not found
    When the category name is 'interior'
    And  product id is 8
    Then the request will not be updated and product not found will be thrown

  Scenario: request edited successfully editing by installer email
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'installer email'
    And the new value is 'mahmoudshoulicarva@gmail.com'
    Then the request will be edited successfully
    And the request list size of the old installer will be 0
    And the request list size of the new installer will be 1

  Scenario: requests edited successfully editing by customer email
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'customer email'
    And the new value is 'mahmoud.shouli.yes@gmail.com'
    Then the request will be edited successfully
    And the request list size of the old customer will be 0
    And the request list size of the new customer will be 1

  Scenario: requests edited successfully editing by date and the date is in the schedule and not reserved
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'date'
    And the new value is '15/2/2024'
    Then the request will be edited successfully
    And the old date can be booked again
    And the new date is reserved

  Scenario: requests edited successfully editing by date and the date is not in the schedule
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'date'
    And the new value is '25/12/2020'
    Then the request will be edited successfully
    And the old date can be booked again
    And a new date is added to the installer schedule and the size of it will be 3
    And the new date is reserved

  Scenario: requests edited successfully editing by car model
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'car model'
    And the new value is 'audi'
    Then the request will be edited successfully and nothing will be thrown

  Scenario: requests edited successfully editing by product id
    When the installer email is 'hala@gmail.com'
    And  the date is '8/2/2024'
    And the edit field is 'product id'
    And the category name is 'interior'
    And the new value is '1'
    Then the request will be edited successfully and nothing will be thrown

