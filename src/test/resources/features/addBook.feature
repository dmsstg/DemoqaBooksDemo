Feature: add book to profile feature

  Background:
    Given the user is logged in
    When the user clicks the "Book Store" menu item
    Then the user is taken to the "Book Store" page

  @ui
  Scenario: User adds an existing book to their profile
    Given the user sees at least 1 book in the book store
    When the user clicks on any book title
    And the user is taken to the "Book" page
    And the user clicks the "Add To Your Collection" button
    And the user sees an alert with the message "Book added to your collection."
    And the user clicks the alert's "OK" button
    And the user clicks the "Profile" menu item
    Then the user should see the added book in their profile
    And the book details should match the expected information
    And the book addition functionality is confirmed