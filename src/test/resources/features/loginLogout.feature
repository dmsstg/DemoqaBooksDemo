Feature: login-logout feature

  @ui
  Scenario: User logs in successfully with valid credentials by clicking Login button on the main page
    Given guest user is on the main page
    When the user clicks the login button on the main page
    And the user is taken to the "Login" page
    And the user enters valid username and password
    And the user clicks the login button
    Then the user is taken to the "Book Store" page
    And the login functionality is confirmed

  @ui
  Scenario: User logs in successfully with valid credentials by going to the Login page from the menu
    Given guest user is on the main page
    When the user clicks the "Login" menu item
    And the user is taken to the "Login" page
    And the user enters valid username and password
    And the user clicks the login button
    Then the user is taken to the "Profile" page
    And the login functionality is confirmed


  @ui
  Scenario Outline: User logs out successfully from the <Internal page> page
    Given the user is logged in
    When the user clicks the "<Internal page>" menu item
    And the user is taken to the "<Internal page>" page
    And the user clicks the logout button
    Then the user is taken to the "Login" page
    And the log out functionality is confirmed

    Examples:
      | Internal page |
      | Login        |
      | Book Store   |
      | Profile      |


