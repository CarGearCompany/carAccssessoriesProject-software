Feature: Search for user by email
  Background: Shared Database
    Given Database is already filled

  Scenario: Search about user
    When the admin enter email is 'mahmoud.shouli.yes@gmail.com'
    Then the object is returned nothing will be thrown

  Scenario: search will failed due to user not found exception
    When the admin enter email is 'raed@gmail.com'
    Then nothing will returned and the user not found exception will be thrown

  Scenario: search will failed due to invalided email format exception
    When the admin enter email is 'email'
    Then nothing will returned and the invalided email format exception will be thrown