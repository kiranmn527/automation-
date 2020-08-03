Feature: Testing the Twitter's twite APIs

  @Debug
  Scenario: Create and access a twite
    Given I am authenticated into Twitter application
    When I make a random twite
    Then the twite is created on my wall
    And I can get <5> twite's made by me
