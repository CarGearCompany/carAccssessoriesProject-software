Feature: Signup
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Sign Up Successfully
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will sign up successfully

    Examples:
      | firstName | lastName  | email            | phoneNumber  | city     | street     | age  | gender   | password    | userType    |
      | 'saleh'   | 'sawalha' | 'saleh@gmai.com' | '0597846668' | 'nablus' | 'asira'    | '21' | 'male'   | 'Saleh@123' | 'installer' |
      | 'haya'    | 'samaneh' | 'haya@gmai.com'  | '0599011043' | 'nablus' | 'rafeedia' | '30' | 'female' | 'Haya@123'  | 'customer'  |

  Scenario Outline: Sign Up Failed Due To User Already Exists Exception
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will fail to sign up and user already exists exception will be thrown

    Examples:
      | firstName | lastName  | email              | phoneNumber  | city     | street     | age  | gender   | password    | userType    |
      | 'saleh'   | 'sawalha' | 'nabeel@gmai.com'  | '0597846668' | 'nablus' | 'asira'    | '21' | 'male'   | 'Saleh@123' | 'installer' |
      | 'haya'    | 'samaneh' | 'mahmoud@gmai.com' | '0599011043' | 'nablus' | 'rafeedia' | '30' | 'female' | 'Haya@123'  | 'customer'  |

  Scenario Outline: Sign Up Failed Due To Weak Password Exception
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will fail to sign up and weak password exception will be thrown

    Examples:
      | firstName | lastName  | email            | phoneNumber  | city     | street     | age  | gender   | password     | userType    |
      | 'saleh'   | 'sawalha' | 'saleh@gmai.com' | '0597846668' | 'nablus' | 'asira'    | '21' | 'male'   | 'Sss73'      | 'installer' |
      | 'haya'    | 'samaneh' | 'haya@gmai.com'  | '0599011043' | 'nablus' | 'rafeedia' | '30' | 'female' | 'hayaloveus' | 'customer'  |

  Scenario Outline: Sign Up Failed Due To Invalid Email Format Exception
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will fail to sign up and invalid email format exception will be thrown

    Examples:
      | firstName | lastName  | email               | phoneNumber  | city     | street     | age  | gender   | password    | userType    |
      | 'saleh'   | 'sawalha' | 'saleh@gmai'        | '0597846668' | 'nablus' | 'asira'    | '21' | 'male'   | 'Saleh@123' | 'installer' |
      | 'haya'    | 'samaneh' | 'haya@somthing.com' | '0599011043' | 'nablus' | 'rafeedia' | '30' | 'female' | 'Haya@123'  | 'customer'  |

  Scenario Outline: Sign Up Failed Due To Invalid Phone Number Exception
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will fail to sign up and invalid phone number exception will be thrown

    Examples:
      | firstName | lastName  | email            | phoneNumber   | city     | street     | age  | gender   | password    | userType    |
      | 'saleh'   | 'sawalha' | 'saleh@gmai.com' | '059784668'   | 'nablus' | 'asira'    | '21' | 'male'   | 'Saleh@123' | 'installer' |
      | 'haya'    | 'samaneh' | 'haya@gmai.com'  | '05990#11043' | 'nablus' | 'rafeedia' | '30' | 'female' | 'Haya@123'  | 'customer'  |

  Scenario Outline: Sign Up Failed Due To Cannot Sign Up As Admin Exception
    When newName is <firstName> <lastName>
    And newContactInfo is <email> <phoneNumber> <city> <street>
    And age is <age>
    And Gender is <gender>
    And newPassword is <password>
    And UserType is <userType>
    Then User will fail to sign up and cannot sign up as admin exception will be thrown

    Examples:
      | firstName | lastName  | email            | phoneNumber  | city     | street  | age  | gender | password    | userType |
      | 'saleh'   | 'sawalha' | 'saleh@gmai.com' | '0597846668' | 'nablus' | 'asira' | '21' | 'male' | 'Saleh@123' | 'admin'  |



