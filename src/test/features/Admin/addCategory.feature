Feature: Add New Category
  Background: Shared Database
    Given Database is already filled

    Scenario: The category will be added successfully
      When the admin enter the 'newCategory' of the category
      Then the category will be added successfully and nothing will be thrown

  Scenario: The category cant be added successfully
    When the admin enter the 'interior' of the category
    Then the category cant be added successfully and ItemAlreadyExist exception will be thrown
