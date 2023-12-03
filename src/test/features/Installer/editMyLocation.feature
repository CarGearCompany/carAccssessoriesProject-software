Feature: Edit Location
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'hala@gmail.com' and 'Hala@123'

  Scenario: Installer will edit the Location Successfully
    When the Installer enter the Location 'newCity' and 'newStreet'
    Then the Location will edit successfully and nothing will be thrown