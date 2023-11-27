Feature: Promote User To Admin
  Background: Shared Database
    Given Database is already filled

 Scenario: promote successfully
   When the admin enters the 'mahmoud.shouli.yes@gmail.com' of the user
   Then the promote will pass and nothing will be thrwon

 Scenario: promote failed due to user not found exception
   When the admin enters the 'raed@gmail.com' of the user
   Then the promote willn't pass due to user not found exception

  Scenario: promote failed due to admins can't be promoted exception
    When the admin enters the 'nabeel@gmail.com' of the user
    Then the promote willn't pass due to admin cannot be promoted exception

  Scenario: promote failed due to invalid email format exception
    When the admin enters the 'nabeel@gmail' of the user
    Then the promote willn't pass due to invalid email format exception
