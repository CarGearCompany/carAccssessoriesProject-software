Feature: remove Request
  Background: Shared Database
    Given Database is already filled

  Scenario: The Request will be removed successfully
    When the admin enter the installer email 'hala@gmail.com'
    And  enters the date '8/2/2024'
    Then the Request will be removed successfully and nothing will be thrown
    And the installer request list size will equals 0
    And the customer request list size will equals 0
    And the date is installer schedule will be not reserved 'false'

  Scenario: The Admin cant remove the request because the date doesn't exists
    When the admin enter the installer email 'hala@gmail.com'
    And  enters the date '15/9/2021'
    Then the request will not be removed and item not found exception will be thrown

  Scenario: the request will not be removed due to user not found exception
    When the admin enters a wrong installer or customer email 'example@gmail.com'
    Then the request will not be removed and User not found exception will be thrown

  Scenario: the request will not be removed due to invalid email format exception
    When the admin enters a wrong installer or customer email format 'example@gmail'
    Then the request will not be removed and invalid email format exception will be thrown







