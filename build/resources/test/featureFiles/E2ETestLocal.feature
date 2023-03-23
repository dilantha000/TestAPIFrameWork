Feature: Send Post request and Verify the Status Code and the Name

  Scenario Outline: Authorised user is able to add or delete books
    Given Add the user details "<code>" "<message>" "<name>"
#   Given Add the user name
    Then Capture the url
    Then Verify the user name
#    And Delete the user
    Examples:
      | code | message | name |
      | 6770 | pass    | bolt |

  Scenario Outline: Get the user data and delte
    Given Get the user data "<url>"
    Then Verify the status code
    Then Delete the all users
    Examples:
      | url    |
      | /posts |

  Scenario Outline: Update the user details
    Given Update the user details "<code>" "<message>" "<name>"
    Then Verify the response message code "<status>"
    Then Verify the updated message "<name>"
    Examples:
      | code | message | name  | status |
      | 6769 | failed  | bolts | 200    |

  @deleteAllUsers
  Scenario Outline: Authorised user is able to add update check states and delete details
    Given Add the user details "<code>" "<message>" "<name>"
    Then Capture the url
    Then Verify the response message code "<status>"
    Then update the user message "<updated_message>"
    Then Verify the updated message "<name>"
#    Then Delete the all users
    Examples:
      | code | message | name  | status | updated_message |
      | 6771 | PASSED  | KEYAN | 200    | Processing      |