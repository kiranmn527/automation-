@Kiran @Td
Feature: WEB-UI Automation Test Scenarios

  Scenario Outline: First Web Test scenario with passed Criteria for User - <Username>
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link
    Examples:
      | Username | Password  |
      | admin   | password   |

  Scenario Outline: First Web Test scenario with Failed Criteria for User - <Username>
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I dont see Logout Link
    Examples:
      | Username | Password |
      | system   | M00nb0y  |

