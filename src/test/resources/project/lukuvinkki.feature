Feature: User can add a reading tip

    Scenario: test database is reset
        Given go to front page
        When in the right page
        Then there is no string

    Scenario: user can add a reading tip
        Given page with reading tip form is selected
        When  the tip form is filled correctly
        Then  system will respond with success

    Scenario: user cannot add a reading tip without title
        Given page with reading tip form is selected
        When  the tip form is filled without title
        Then  system will respond with failure

    Scenario: tip list works correctly
        Given page with reading tip form is selected
        When  all tips are added
        Then  system will respond with correct list