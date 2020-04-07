Feature: User can add a reading tip

    Scenario: user can add a book reading tip
        Given form is selected
        When  the bookform is filled
        Then  system will respond with success

    Scenario: user can add a video tip
        Given form is selected
        When  the videoform is filled
        Then  system will respond with success
