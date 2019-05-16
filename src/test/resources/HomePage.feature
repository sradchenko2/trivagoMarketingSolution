Feature: HomePage functionality
  As a user
  I want HomePage displayed
  So that I can find hotel

  Scenario: HomePage ReadMore button at top banner land on appropriate page
    Given user is on homepage
    When user tap on ReadMore button
    Then coolestHotels page displayed

  Scenario: No result message should shows when no search result was found
    Given at search bar
    And incorrect phrase entered
    When user submit search
    Then search result page displayed
    And "No results" message is displayed

  Scenario: When user tap on search icon at HomeScreen search bar shows
    Given user is on homepage
    And search icon is displayed
    When user tap on search icon
    Then search bar displayed

  Scenario: When user enter and apply search phrase search result screen shows
    Given user at search bar
    And search phrase entered
    When user tap submit search
    Then search result page displayed
    And search result match search phrase

  Scenario: User is able to subscribe to a news
    Given user at bottom of HomePage
    And correct email entered in subscribe email field
    When user tap InspireMe button
    Then "You are now checked-in!" message is displayed

  Scenario: User is able to Navigate to a destination through the menu
    Given user at HomePage
    And top left menu opened
    When user tap on destination
    Then destination page displayed

