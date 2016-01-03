Feature: Storing a resources to the mongodb storage

  @happy
  Scenario: Providing storing resources to the mongodb
    Given The resources
    And The storage outbound gateway
    When The storage outbound gateway store method is called
    Then The resource will be stored