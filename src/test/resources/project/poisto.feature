Feature: User can delete a reading tip

    Scenario: user can delete a reading tip
        Given frontpage is opened
        When  the tip is deleted
        Then  system will respond with delete success
