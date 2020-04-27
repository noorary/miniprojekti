Feature: User can add a reading tip

    Scenario: user can add a reading tip
        Given page with reading tip form is selected
        When  the tip form is filled correctly
        Then  system will respond with success

    Scenario: user cannot add a reading tip without title
        Given page with reading tip form is selected
        When  the tip form is filled without title
        Then  system will respond with failure


    Scenario: added tip has correct info
        Given  a reading tip with title "Gordon cooks", author "Gordon Ramsay", description "Tasty Bacon" and url "www.gordonramsay" is added
        When  submit tip form
        Then  results contain tip with title "Gordon cooks", author "Gordon Ramsay", description "Tasty Bacon" and url "www.gordonramsay"
