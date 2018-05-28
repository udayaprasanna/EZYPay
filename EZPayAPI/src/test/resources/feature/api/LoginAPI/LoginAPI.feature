@LoginAPI
Feature: LoginAPI tests

  Scenario Outline: Login API test
    Given I have a Login API POST request <filename> with <username> username and <password> password
    When I send a POST request to Login API
    Then I should get the resposne with <responsevalidationfieldsandvalue>

    Examples: 
      | username  | password | responsevalidationfieldsandvalue                                                    | filename                       |
      | test-user | abc123   | 200,username:test-user,password:abc123                                              | LoginAPI_request               |
      | test-user | invalid  | 401,Code:UnauthorizedError,Message:UnauthorizedError: invalid username or password  | LoginAPI_request               |
      | invalid   | abc123   | 401,Code:UnauthorizedError,Message:UnauthorizedError: invalid username or password  | LoginAPI_request               |
      | invalid   | invalid  | 400,Code:BadRequestError,Message:BadRequestError: username or password not provided | LoginAPI_invalidSchema_request |
      | invalid   | invalid  | 500,Code:InternalServerError,Message:An internal server error occurred.             | invalidJSON                    |
