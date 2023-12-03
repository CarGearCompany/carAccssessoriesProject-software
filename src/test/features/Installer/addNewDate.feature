Feature: Add New Date
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'hala@gmail.com' and 'Hala@123'

  Scenario: Installer will Add valid Data to his scheduleList successfully
    When the Installer enter the new Date '12/4/2025'
    Then the date will added successfully and nothing will be thrown

  Scenario: Installer will Add not valid Data to his scheduleList
    When the Installer enter the new Date '15/2/2024'
    Then the date willNot be added and AlreadyReservedDate Exception will be thrown