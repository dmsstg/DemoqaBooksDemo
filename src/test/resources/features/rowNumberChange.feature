Feature: change number of displayed rows on the profile page

  Background:
    Given the user is logged in
    When the user clicks the "Profile" menu item
    Then the user is taken to the "Profile" page

  @ui
  Scenario: User selects rows number to display
    Given the user is on the "Profile" page
    When the user selects the number of rows to display
      | 10  |
      | 20  |
      | 25  |
      | 50  |
      | 100 |
      | 5   |
    Then the displayed rows match the selected count
    And the functionality of selecting the number of rows is confirmed
