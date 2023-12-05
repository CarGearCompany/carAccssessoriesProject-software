Feature: Remove Product
  Background: Shared Database
    Given Database is already filled

  Scenario: Remove Product Successfully
    When the name of the  is Valid and the category to be removed is 'interior'
    And the product id is exist and equals 1
    Then this product will be removed and the size of products will be 5

  Scenario: Remove product Failed Due to Category Not Found Exception
    When the name of the  is invalid and the category to be removed is 'newcategory'
    And the product id is exist and equals 1
    Then nothing will be removed and an category not found exception will be thrown

  Scenario: Remove product Failed Due to Product Not Found Exception
    When the name of the  is Valid and the category to be removed is 'interior'
    And the product id does not exist and equals 5
    Then nothing will be removed and an Product not found exception will be thrown
