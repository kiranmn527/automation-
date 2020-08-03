Feature: Testing the error handling
  in the framework

  Scenario Outline: Web Authenication fail
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then I see failed status

    Examples:
      | Username | Password |
      | system   | M00nb0y  |