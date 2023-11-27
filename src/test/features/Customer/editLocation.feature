Feature: Edit Location
  Background: Shared Database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'

  Scenario: Customer will edit the Location Successfully
    When the customer enter the Location 'newCity' and 'newStreet'
    Then the Location will edit successfully and nothing will be thrown