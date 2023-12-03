Feature: Login
  Background: Shared Database
    Given Database is already filled



 Scenario Outline: Login Successfully
   When email is <email>
   And password is <password>
   Then the user will login successfully

   Examples:
     | email                  | password      |  |
     | 'xnabeel972@gmail.com' | 'Nabeel@123'  |  |
     | 'mahmoud@gmail.com'    | 'Mahmoud@123' |  |



  Scenario Outline: Login Failed Due To User Not Found exception
    When email is <email>
    And password is <password>
    Then the user will fail to login due to not existing email and user not found exception will be thrown

    Examples:
      | email                | password      |
      | 'xnabeel@gmail.com'  | 'Nabeel@123'  |
      | 'mahmoud1@gmail.com' | 'Mahmoud@123' |


  Scenario Outline: Login Failed Due To Wrong Password
    When email is <email>
    And password is <password>
    Then the user will fail to login due to wrong password

    Examples:
      | email               | password  |  |
      | 'xnabeel972@gmail.com'  | 'nabeel'  |  |
      | 'mahmoud@gmail.com' | 'mahmoud' |  |

