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
    Given Get the email "<author>" and "<url>" and "<title>"
    Then Verify the city name
    Examples:
      | author   | url     | title       |
      | typicode | posts/1 | json-server |

  Scenario Outline: Add new author details
    Given Update new "<author>" details
    Then Verify the city name
    Examples:
      | author |
      | james  |

#  Scenario : Test maps
#    When The Valid Instruction is updated with
#      | author   | url     | title       |
#      | typicode | posts/1 | json-server |
#
#    Then Verify the city name

  Scenario Outline: Update city details
    Given Update the email "<username>" and "<password>"
    Then Verify the city name
    Examples:
      | username | password |
      | typicode | posts/1  |