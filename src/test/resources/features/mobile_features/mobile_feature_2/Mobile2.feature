Feature: Testing the login feature
  so we can verify no unauthorised user
  can login to the system


  Scenario Outline: Mobile Authenication 2
    Given Open Application and Enter url
    When user enter "<Username>" and "<Password>"
    Then verify I see Logout Link

    Examples:
    |Username|Password|
    |system |M00nb0y |