Feature: Dummy Rest API Functionality Scenarios

  Scenario Outline: Dummy Rest Api GET Students
    Given Get Call to "<url>"
    Then Response Code "<responseMessage>" is validated

    Examples:
      | url       | responseMessage |
      | /users/23 | 200             |

  Scenario Outline: verify name display
    Given Get Call to "<url>"
    Then Response should display "<name>"

    Examples:
      | url          | name  |
      | users?page=2 | James |

#  Scenario : Verify bookshop details
#    Given Get the Author details status code


  Scenario Outline: Verify the city name
    Given Get the email "<author>" and "<url>"
    Then Verify the city name
    Examples:
      | author   | url      |
      | typicodee | posts/1 |