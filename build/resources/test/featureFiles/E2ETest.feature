Feature: End to End test

  Background: User generate token for authorization
      Given I am an authorized user

    Scenario: Authorised user is able to add or delete books
      Given A list of books are available
      When I add a book to my reading list
      Then The book is added
#      When I remove a book from my reading list
#      Then The book is removed