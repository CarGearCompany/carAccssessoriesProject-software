Feature: Search for category by name
  Background: Shared Database
    Given Database is already filled

  Scenario: Search about category
    When the admin should enter name of category  'interior'
    Then the object is returned and nothing will be thrown

  Scenario: search will failed due to item not found exception
    When the admin should enter name of category  'category12'
    Then nothing will returned and the item not found exception will be thrown
