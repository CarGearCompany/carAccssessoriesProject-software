Feature: Request Installation
  Background: shared database
    Given Database is already filled
    Given the user is already logged in with 'mahmoud.shouli.yes@gmail.com' and 'Saleh@123'


  Scenario: the Service is requested successfully
    When the customer wants to reuest a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters availble date '15/12/2023'
    And enters the requested product 'Spoiler'
    Then the service will be requested and the request will be added to the installer's request list and its size will be 1 and the customer's request list and its size will be 1

  Scenario: the request fails due to no product found
    When the customer wants to reuest a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters availble date '15/12/2023'
    And enters the requested product 'car rims'
    Then the request will fail and the ProductNotFound Exception  will be thrown

  Scenario: the request fails due to already booked date
    When the customer wants to reuest a service
    And enters installerEmail 'mahmoudshoulicarva@gmail.com'
    And enters CarModel 'Audi R8'
    And enters availble date '7/1/2024'
    And enters the requested product 'Spoiler'
    Then the request will fail due to already booked date