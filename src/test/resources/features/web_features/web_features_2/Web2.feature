Feature: Testing the Box Management feature

  Scenario Outline: Create a new Box
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    And Create a Box
    Then Box is Created in the system
    And I can access the box name in Login Action
    And I can access the box name in Login Step defination

    Examples:
    |Username|Password|
    |system |M00nb0y |