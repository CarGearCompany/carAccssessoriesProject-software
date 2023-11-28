Feature: Search for user
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Search for a user by specific credential
    When search type is  <searchType>
    And value is  <value>
    Then the users are found and number of results must be <resultSize>

    Examples:
      | searchType     | value               | resultSize |
      | 'gender'       | 'male'              | 4          |
      | 'email'        | 'mahmoud@gmail.com' | 1          |
      | 'city'         | 'nablus'            | 2          |
      | 'gender'       | 'female'            | 2          |
      | 'age'          | '21'                | 2          |
      | 'user type'    | 'customer'          | 2          |
      | 'phone number' | '0591234567'        | 1          |
      | 'last name'    | 'Qasem'             | 1          |

