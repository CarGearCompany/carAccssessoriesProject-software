Feature: Edit Password
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'hala@gmail.com' and 'Hala@123'

  Scenario: Installer edit the password Successfully
    When the Installer enter the password 'Pass@123'
    Then the password will edit successfully and nothing will be thrown

  Scenario:  Installer can't edit the password
    When the Installer enter the password 'pass123'
    Then the password failed to edit and nothing weak password exception will be thrown