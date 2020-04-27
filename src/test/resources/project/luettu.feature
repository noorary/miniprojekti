Feature: user can mark tip as read


  Scenario: user marks tip as read
    Given frontpage is opened
    When  the tip is marked as read
    Then  system will respond with marked as read true

  Scenario: user presses checked button twice
    Given frontpage is opened
    When  the tip is marked as read and toggled again
    Then  system will respond with marked as read false

  Scenario: when user marks tips as read timestamp is saved
    Given frontpage is opened
    When  the tip is marked as read
    Then  system will save timestamp