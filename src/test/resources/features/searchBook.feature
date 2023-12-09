Feature: book search feature
  Background:
    Given the user is logged in
    And the user is taken to the "Book Store" page


  @ui
  Scenario: User searches for the existing book
    Given the user sees an empty search box
    When the user enters the full title of an existing book into the search box
    Then the user should see only books with the searched title in the result grid
    And the search functionality is confirmed
