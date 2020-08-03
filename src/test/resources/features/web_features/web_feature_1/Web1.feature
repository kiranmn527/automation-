@Kiran @Td
Feature: Testing the login feature so we can verify no unauthorised user can login to the system

  Scenario Outline: Web first Test
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link
    Examples:
      | Username | Password  |
      | admin   | password   |

  Scenario Outline: Web first Test 22
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link
    Examples:
      | Username | Password  |
      | admin   | password   |

  Scenario Outline: Web Second Test failure
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I dont see Logout Link
    Examples:
      | Username | Password |
      | system   | M00nb0y  |

  Scenario Outline: Web Second Test failure 2
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I dont see Logout Link
    Examples:
      | Username | Password |
      | system   | M00nb0y  |