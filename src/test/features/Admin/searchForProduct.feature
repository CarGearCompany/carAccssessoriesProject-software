Feature: Search for product
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Search for a product by specific credential
    When search type is <searchType>
    And value is <value>
    Then the products are found and number of results must be <resultSize>

  Examples:
    | searchType     | value          | resultSize |
    | 'id'           | '3'            | 1          |
    | 'name'         | 'Spoiler'      | 1          |
    | 'description'  | 'description5' | 1          |
    | 'price'        | '180'          | 1          |
    | 'quantity'     | '20'           | 1          |
    | 'availability' | 'false'        | 1          |
    | 'availability' | 'true'         | 5          |
    | 'id'           | '9'            | 0          |


