Feature: Checking health for different services

  Scenario: Checking health for customer services - Success
    When healthcheck endpoint for customer service is invoked
    Then a status of 200 is returned
    And the healthcheck response has status "UP"