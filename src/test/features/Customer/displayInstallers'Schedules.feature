Feature: Display All Installers' Schedules
  Background: shared database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: the schedules are printed successfully
    When the customer wants to see the schedules
    Then the schedules will be printed and the result size will be 4

