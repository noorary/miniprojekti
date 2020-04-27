Feature: Listing works correctly

  Scenario: tip list works correctly
    Given page with reading tip form is selected
    When  all tips are added
    Then  system will respond with correct list

  Scenario: frontpage listing works correctly
    Given frontpage is opened
    Then  frontpage has all tips added