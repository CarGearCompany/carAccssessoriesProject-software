Feature: Logout
  Scenario Outline: Logout
    Given Database is already filled
    And a user is already logged in with <email> and <password>
    When user logged out
    Then the current user will be null

    Examples:
      | email               | password      |
      | 'xnabeel972@gmail.com'  | 'Nabeel@123'  |
      | 'mahmoud@gmail.com' | 'Mahmoud@123' |