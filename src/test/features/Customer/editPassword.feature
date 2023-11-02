Feature: Edit Password
  Background: Shared Database
    Given Database is already filled

  Scenario: Customer edit the password Successfully
    When the customer enter the password 'Pass@123'
    Then the password will edit successfully and nothing will be thrown

  Scenario:  Customer can't edit the password
    When the customer enter the password 'pass123'
    Then the password falied to edit and nothing weak password exception will be thrown