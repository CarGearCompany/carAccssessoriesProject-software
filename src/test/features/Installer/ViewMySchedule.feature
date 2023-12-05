Feature: Installer Wants To Display His scheduleList
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'hala@gmail.com' and 'Hala@123'


  Scenario: installer chooses to list his scheduleList
    When the installer wants to list all his scheduleList
    Then the result size of the scheduleList must be 2

  Scenario: installer chooses to add new date to his schedule
    When the installer add a valid date '12/4/2025'
    Then the result size of the scheduleList must be 3