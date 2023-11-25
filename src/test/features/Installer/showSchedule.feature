Feature:  Show Installer's Schedule
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Installer's schedule is printed successfully
    When email is <email>
    And password is <password>
    Then the installer's schedule will be printed successfully

    Examples:
      | email             | password      |
      | 'asamr@gmail.com' | 'Mahmoud@123' |
      | 'hala@gmail.com'  | 'Hala@123'    |