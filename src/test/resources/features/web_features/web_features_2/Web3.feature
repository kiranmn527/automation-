Feature: Testing the login feature
  so we can verify no unauthorised user
  can login to the system

  @Sequential
  Scenario Outline: Web Authenication 3
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link

    Examples:
    |Username|Password|
    |system |M00nb0y |



  @Sequential
  Scenario Outline: Web Authenication 3
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link

    Examples:
      |Username|Password|
      |krian |M00nb0y |