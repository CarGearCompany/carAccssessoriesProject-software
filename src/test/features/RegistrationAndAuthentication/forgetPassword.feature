Feature: Forget Password
  Background: shared database
    Given Database is already filled



  Scenario: password reset successfully
    When user enters email  'mahmoud.shouli.yes@gmail.com'
    And enters '12345' he got from email '12345'
    And enters new password 'Carva@123'
    And confirms the new password 'Carva@123'
    Then the password is reset and equals 'Carva@123'



  Scenario: password failed to reset due to user not found exception
    When user enters email  'mahmoud.shouli.@gmail.com'
    And enters '12345' he got from email '12345'
    And enters new password 'Carva@123'
    And confirms the new password 'Carva@123'
    Then user not found exception will be thrown


  Scenario: password failed to reset due to invalid email format  exception
    When user enters email  'mahmoud.shouli.yes@blaa.com'
    And enters '12345' he got from email '12345'
    And enters new password 'Carva@123'
    And confirms the new password 'Carva@123'
    Then invalid email format exception will be thrown


  Scenario: password failed to reset due to wrong code entered
    When user enters email  'mahmoud.shouli.yes@gmail.com'
    And enters '54321' he got from email '12345'
    And enters new password 'Carva@123'
    And confirms the new password 'Carva@123'
    Then not equal codes exception will be thrown


  Scenario: password failed to reset due to mismatch between the 2 passwords
    When user enters email  'mahmoud.shouli.yes@gmail.com'
    And enters '12345' he got from email '12345'
    And enters new password 'Carva@123'
    And confirms the new password 'Carva@12'
    Then not equal passwords exception will be thrown


  Scenario: password failed to reset due to weak password
    When user enters email  'mahmoud.shouli.yes@gmail.com'
    And enters '12345' he got from email '12345'
    And enters new password 'Carva'
    And confirms the new password 'Carva'
    Then weak password exception will be thrown



  Scenario: email is sent successfully
    When user enters email  'mahmoud.shouli.yes@gmail.com'
    Then email verification is sent