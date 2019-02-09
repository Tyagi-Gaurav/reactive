@wip
Feature: Retrieving a delivery spec

Scenario: Customer should be provided with a list of routes when booking a cargo
  Given the registered user intends to see a list of available routes
  When the registered user attempts to retrieve a list of available routes
  Then the list of available routes must contain different legs
  And each available route must contain price for corresponding route
