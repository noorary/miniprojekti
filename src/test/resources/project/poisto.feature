Feature: User can delete a reading tip

    Scenario: user can delete a reading tip
        Given page with reading tip form is selected
        When  the tip is deleted
        Then  system will respond with delete success
