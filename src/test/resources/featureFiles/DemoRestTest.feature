Feature: Dummy Rest API Functionality Scenarios

  Scenario Outline: Dummy Rest Api GET Students
    Given Get Call to "<url>"
    Then Response Code "<responseMessage>" is validated

    Examples:
      | url       | responseMessage |
      | /users/23 | 404             |


  Scenario Outline: verify name display
    Given Get Call to "<url>"
    Then Response should display "<name>"

    Examples:
      | url          | name    |
      | users?page=2 | Lindsay |


  Scenario Outline: Verify the city name
    And Get the email "<author>" and "<url>" and "<title>"
    Then Verify the city name
    Examples:
      | author   | url     | title       |
      | typicode | posts/1 | json-server |


  Scenario Outline: Update city details
    Given Update the email "<username>" and "<password>"
    Then Verify the city name
    Examples:
      | username | password |
      | typicode | posts/1  |