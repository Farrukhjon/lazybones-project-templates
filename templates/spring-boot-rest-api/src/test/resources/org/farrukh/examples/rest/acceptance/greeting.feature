Feature: The REST controller for greeting message.

  @happy
  Scenario: Providing get hello world greeting
    Given The greeting url
    And The proper request is created
    When The client is called the get method using the created request
    Then The greeting REST inbound gateway returns the response
    And The Hello World! is returned upon response
    And The proper headers is returned