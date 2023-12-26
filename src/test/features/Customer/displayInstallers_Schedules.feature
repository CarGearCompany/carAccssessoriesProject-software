Feature: Customer Wants To See The Schedules
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: customer chooses to list all schedules
    When the customer wants to see the schedules
    Then the result size of the schedules is 4

  Scenario: customer chooses to list all schedules
    When the customer with 'mahmoud.shouli.yes@gmail.com' sees an update after the installer is who logged with 'mahmoudshoulicarva@gmail.com' and 'Mahmoud@123' adds a new date
    And the date is valid date '15/9/2024'
    Then the result size of the schedules must be 5