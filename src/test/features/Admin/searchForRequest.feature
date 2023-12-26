Feature: Search for request
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Search for a request by specific credential
    When the search type is <searchType>
    And the value is  <value>
    Then products are found and number of results must be <resultSize>

  Examples:
    | searchType        | value            | resultSize |
    | 'customer email'  | 'jana@gmail.com' | 1          |
    | 'installer email' | 'hala@gmail.com' | 1          |
    | 'date'            | '8/2/2024'       | 1          |
    | 'car model'       | 'bmw'            | 1          |
    | 'product id'      | 0                | 1          |
    | 'customer email'  | 'flan@gmail.com' | 0          |
    | 'test'            | 'test'           | 0          |


