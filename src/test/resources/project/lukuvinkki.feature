Feature: User can add a reading tip

    Scenario: user can add a book reading tip
        Given form is selected
        When  the bookform is filled
        Then  system will respond with success

    Scenario: user can add a video tip
        Given form is selected
        When  the videoform is filled
        Then  system will respond with success

    Scenario: user cannot add a book reading tip without title
        Given form is selected
        When  the bookform is filled without title
        Then  system will respond with failure

    Scenario: user cannot add a video tip without title
        Given form is selected
        When  the videoform is filled without title
        Then  system will respond with failure