Feature: Edit Products
  Background: Shared Database
    Given Database is already filled




  Scenario Outline: Products edited successfully
    When category is <category>
    And id is <id>
    And edit field is <editType>
    And new value is <newVal>
    Then the field will be edited successfully

    Examples:

      | category     | id | editType       | newVal          |
      | 'interior'   | 0  | 'name'         | 'Gear Cover'    |
      | 'exterior'   | 3  | 'price'        | '280'           |
      | 'electronic' | 4  | 'description'  | 'modern system' |
      | 'interior'   | 1  | 'quantity'     | '55'            |
      | 'electronic' | 5  | 'availability' | 'false'         |


  Scenario: Admin tries to change product id but fails
      When category is 'interior'
      And id is 0
      And edit field is 'ID'
      And new value is '2'
      Then he will fail to edit and cannot edit id exception will be thrown


  Scenario: Product is not found
    When category is 'interior'
    And id is 5
    And edit field is 'price'
    And new value is '100'
    Then it will fail due to product not found exception
