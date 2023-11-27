Feature: Remove User
  Background: Shared Database
    Given Database is already filled

  Scenario: Remove User Successfully
    When the email of the user to be removed is 'mahmoud.shouli.yes@gmail.com'
    Then this user will be removed

  Scenario: Remove User Failed Due to User Not Found Exception
    When the email of the user to be removed is 'raed@gmail.com'
    Then nothing will be removed and a user not found exception will be thrown

  Scenario: Remove User Failed Due to Admin Cannot Be Removed Exception
    When the email of the user to be removed is 'nabeel@gmail.com'
    Then nothing will be removed and an admin cannot be removed exception will be thrown

  Scenario: Remove User Failed Due to Invalid email Exception
    When the email of the user to be removed is 'nabeel@.com'
    Then nothing will be removed and an invalid email format exception  will be thrown