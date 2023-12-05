Feature: Remove Category
  Background: Shared Database
    Given Database is already filled

  Scenario: Remove Category Successfully
    When the name of the category to be removed is 'interior'
    Then this category will be removed and the size of categories will be 2

  Scenario: Remove User Failed Due to Item Not Found Exception
    When the name of the category to be removed is 'category1'
    Then nothing will be removed and an item not found exception will be thrown
