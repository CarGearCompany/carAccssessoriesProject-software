Feature: Request Installation
  Background: shared database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: the service is requested successfully
    When the customer wants to request a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters available date '15/12/2023'
    And enters category 'electronic'
    And enters the product id 4
    Then the service will be requested and the request will be added to the installer's and customer's request list and their size will be 1


  Scenario: the request fails due to no category found
    When the customer wants to request a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters available date '15/12/2023'
    And enters category 'cosmetics'
    And enters the product id 0
    Then the request will fail and the category not found exception  will be thrown

  Scenario: the request fails due to no product found
    When the customer wants to request a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters available date '15/12/2023'
    And enters category 'interior'
    And enters the product id 3
    Then the request will fail and the product not found exception  will be thrown

  Scenario: the request fails due to installer email not found
    When the customer wants to request a service
    And enters installerEmail 'jamalhamadneh@gmail.com'
    And enters CarModel 'Audi R8'
    And enters available date '15/12/2023'
    And enters category 'interior'
    And enters the product id 0
    Then the request will fail and the user not found exception  will be thrown

  Scenario: the request fails due to invalid email format
    When the customer wants to request a service
    And enters installerEmail 'mahmoud@gma.com'
    And enters CarModel 'Audi R8'
    And enters available date '15/12/2023'
    And enters category 'interior'
    And enters the product id 0
    Then the request will fail and the invalid email format exception  will be thrown


  Scenario: the request fails due to already booked date exception
    When the customer wants to request a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters available date '7/1/2024'
    And enters category 'interior'
    And enters the product id 0
    Then the request will fail and the date already booked exception  will be thrown