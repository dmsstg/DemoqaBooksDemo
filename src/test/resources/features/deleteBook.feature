Feature: delete book from profile feature

  Background:
    Given the user has 1 book[s] in their profile
    And the user is logged in
    When the user clicks the "Profile" menu item
    Then the user is taken to the "Profile" page
    And the user sees 1 book[s] in their profile

  @ui
  Scenario: User deletes an existing book from their profile
    Given the user is on the "Profile" page
    When the user clicks the trash can icon in the line with the book that needs to be deleted
    And the user click the OK button on popup
    And the user sees an alert with the message "Book deleted."
    And the user clicks the alert's "OK" button
    Then the user shouldn't be able to see the deleted book in their profile
    And the book deletion functionality is confirmed