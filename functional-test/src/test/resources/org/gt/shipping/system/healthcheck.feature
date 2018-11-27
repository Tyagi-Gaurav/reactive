Feature: Checking health for different services

  Scenario: Checking health for customer services - Success
    Given Customer Service is available to respond
    When healthcheck endpoint for customer service is invoked
    Then a status of 200 is returned