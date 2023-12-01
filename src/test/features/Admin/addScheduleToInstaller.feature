Feature: Add New schedule To Installer
  Background: Shared Database
    Given Database is already filled

  Scenario: The schedule will added Successfully
    When the admin enter the date '25/5/2025'
    And the admin enter the email 'hala@gmail.com'
    Then the schedule will be added successfully

  Scenario: The schedule cant be added successfully due to user not found
    When the admin enter the email 'flan@gmail.com'
    Then the schedule cant be added successfully and UseNotFound exception will be thrown

  Scenario: The schedule cant be added successfully due to not valid email
    When the admin enter the email 'flan@gmail'
    Then the schedule cant be added successfully and NotValidFormat exception will be thrown
