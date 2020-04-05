Feature: User can add a reading tip

    Scenario: user can add a book reading tip
        Given book form is selected
        When  the form is filled
        Then  system will respond with success

    Scenario: user can add a video tip
        Given video form is selected
        When  title is added
        Then  system will respond with success
